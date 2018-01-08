package football.udfs.validation;

import football.AutowiredBroadcast;
import football.broadcasted_configs.CodesConfig;
import football.udfs.RegisterUdf;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF3;

import java.util.Map;

@RegisterUdf
public class CodeFilterUdf implements UDF3<Integer, String, String, Boolean> {
    @AutowiredBroadcast
    private Broadcast<CodesConfig> broadcast;

    @Override
    public Boolean call(Integer code, String firstPlayer, String secondPlayer) throws Exception {
        Map<Integer, Integer> codesPlayersCountMap = broadcast.getValue().codesPlayersCountMap;
        if (!codesPlayersCountMap.containsKey(code))
            return true;

        int firstPlayerExistance = (!firstPlayer.isEmpty()) ? 1 : 0;
        int secondPlayerExistance = (!secondPlayer.isEmpty()) ? 1 : 0;
        int operationPlayersCount = firstPlayerExistance + secondPlayerExistance;

        return codesPlayersCountMap.get(code) == operationPlayersCount;
    }
}
