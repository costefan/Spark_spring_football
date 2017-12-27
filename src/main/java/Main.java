import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.rdd.RDD;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    private static StructType createSchema() {

        return DataTypes.createStructType(new StructField[] {
                DataTypes.createStructField("code", DataTypes.IntegerType, false),
                DataTypes.createStructField("from", DataTypes.StringType, false),
                DataTypes.createStructField("to", DataTypes.StringType, true),
                DataTypes.createStructField("eventTime", DataTypes.StringType, false),
                DataTypes.createStructField("stadion", DataTypes.StringType, false),
                DataTypes.createStructField("startTime", DataTypes.StringType, false),
        });
    }

    private static String parseValue(String value) {
        Pattern pattern = Pattern.compile("^([a-zA-Z]+)=(.+)");
        Matcher m  = pattern.matcher(value);
        if (m.matches()) {
            return m.group(2);
        } else {
            return value;
        }
    }

    public static void main(String[] args) throws Exception {
        SparkConf sparkConf = new SparkConf()
                .setAppName("Football app")
                .setMaster("local[*]")
                .set("spark.driver.host", "localhost");

        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        SQLContext sqlContext = new SQLContext(sc);
        JavaRDD<String> rdd = sc.textFile("data/rawData.txt");

        rdd.persist(StorageLevel.MEMORY_AND_DISK());

        JavaRDD<Row> rowRdd = rdd.map(String::toLowerCase).filter(line -> line.length() != 0).map(line -> {
            ArrayList<String> data = new ArrayList<String>(Arrays.asList(line.split(";")));
            List<String> parsedData = data
                    .stream()
                    .map(Main::parseValue)
                    .collect(Collectors.toCollection(ArrayList::new));
            System.out.println(parsedData);
            return RowFactory.create(
                    Integer.parseInt(parsedData.get(0)), parsedData.get(1), parsedData.get(2), parsedData.get(3),
                    parsedData.get(4), parsedData.get(5));
        });

        StructType schema = createSchema();

        DataFrame df = sqlContext.createDataFrame(rowRdd, schema);
        df.show();
    }
}
