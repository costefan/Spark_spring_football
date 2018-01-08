package football.mutators.impl;

import football.mutators.Mutator;
import football.udfs.mutation.AddFootballTimeUdf;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.functions;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.*;

@Service
public class FootballTimesMutator implements Mutator {
    @Override
    public DataFrame mutate(DataFrame df) {
        return df.withColumn("footbalTime",
                functions.callUDF(AddFootballTimeUdf.class.getName(),
                        col("eventTime")));
    }
}
