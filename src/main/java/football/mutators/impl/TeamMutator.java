package football.mutators.impl;

import football.mutators.Mutator;
import football.udfs.mutation.AddPlayersTeamUdf;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.*;

@Service
public class TeamMutator implements Mutator{
    @Override
    public DataFrame mutate(DataFrame df) {
        return df.withColumn("team", callUDF(AddPlayersTeamUdf.class.getName(), col("from")));
    }
}
