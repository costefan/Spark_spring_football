package football;

import football.services.DataFrameBuilder;
import football.services.mutation.MutatorService;
import football.services.validation.ValidationService;
import org.apache.spark.sql.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FootballProcessingImpl implements FootballProcessing {
    @Autowired
    private ValidationService validator;

    @Autowired
    private DataFrameBuilder dataFrameBuilder;

    @Autowired
    private MutatorService mutator;

    @Override
    public DataFrame run() {

        DataFrame df = dataFrameBuilder.create("data/rawData.txt");
        df = validator.validate(df);

        df = mutator.run(df);

        df.write()
                .mode("overwrite")
                .save("./data/" + "result.parquet");

        return df;
    }
}
