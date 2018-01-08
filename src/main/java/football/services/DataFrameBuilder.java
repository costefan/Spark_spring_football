package football.services;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DataFrameBuilder {

    @Autowired
    private JavaSparkContext sc;


    @Autowired
    private SQLContext sqlContext;

    private static List<String> columns;

    @Value("${columnNames}")
    public void setColumns(String[] columns) {
        DataFrameBuilder.columns = Stream.of(columns).map(String::toLowerCase).collect(Collectors.toList()) ;
    }

    private static StructType createSchema() {

        return DataTypes.createStructType(new StructField[] {
                DataTypes.createStructField("code", DataTypes.IntegerType, false),
                DataTypes.createStructField("from", DataTypes.StringType, false),
                DataTypes.createStructField("to", DataTypes.StringType, true),
                DataTypes.createStructField("eventTime", DataTypes.StringType, false),
                DataTypes.createStructField("stadion", DataTypes.StringType, false),
        });
    }

    public static String parseValue(String value) {
        Pattern pattern = Pattern.compile("^([a-zA-Z]+)=(.+)");
        Matcher m  = pattern.matcher(value);
        // if column name is valid and string is valid
        if (m.matches() && columns.contains(m.group(1))) {
            return m.group(2);
        } else {
            return "";
        }
    }

    public DataFrame create(String filePath) {

        JavaRDD<String> rdd = sc.textFile(filePath);

        rdd.persist(StorageLevel.MEMORY_AND_DISK());
        JavaRDD<Row> rowRdd = rdd.map(String::toLowerCase).filter(line -> line.length() != 0).map(line -> {
            ArrayList<String> data = new ArrayList<String>(Arrays.asList(line.split(";")));
            List<String> parsedData = data
                    .stream()
                    .map(DataFrameBuilder::parseValue)
                    .collect(Collectors.toCollection(ArrayList::new));

            return RowFactory.create(
                    Integer.parseInt(parsedData.get(0)), parsedData.get(1), parsedData.get(2), parsedData.get(3),
                    parsedData.get(4));
        });

        StructType schema = createSchema();

        DataFrame df = sqlContext.createDataFrame(rowRdd, schema);
        return df;
    }
}
