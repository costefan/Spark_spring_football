package football;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.*;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import org.apache.spark.storage.StorageLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class FootballProcessingImpl implements FootballProcessing {
    @Autowired
    private ValidationService validator;

    @Autowired
    private DataFrameBuilder dataFrameBuilder;


    @Override
    public DataFrame run() {

        DataFrame df = dataFrameBuilder.create();
        validator.validate(df);
        return df;
    }
}
