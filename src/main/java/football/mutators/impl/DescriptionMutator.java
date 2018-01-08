package football.mutators.impl;

import football.mutators.Mutator;
import football.udfs.mutation.AddDescriptionUdf;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.*;

@Service
public class DescriptionMutator implements Mutator {

    @Override
    public DataFrame mutate(DataFrame df) {

        return df.withColumn("description",
                callUDF(AddDescriptionUdf.class.getName(), col("code")));
    }
}
