package football.validators.impl;

import football.AutowiredBroadcast;
import football.aop.ShowDfInTheBeginning;
import football.aop.ShowDfInTheEnd;
import football.broadcasted_configs.PlayersConfig;
import football.validators.Validator;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.col;

@Service
public class PlayersValidator implements Validator {

    @AutowiredBroadcast
    private Broadcast<PlayersConfig> broadcast;

    @Override
    @ShowDfInTheBeginning
    @ShowDfInTheEnd
    public DataFrame filter(DataFrame dataFrame) {
        return dataFrame
                .filter(col("from").isin(
                        broadcast.getValue().allPlayers.stream().toArray(String[]::new)))
                .filter(col("to").isin(
                        broadcast.getValue().allPlayers.stream().toArray(String[]::new)));
    }
}
