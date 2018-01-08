package football.udfs.mutation;

import football.AutowiredBroadcast;
import football.broadcasted_configs.PlayersConfig;
import football.udfs.RegisterUdf;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;

import java.util.List;
import java.util.Map;

@RegisterUdf
public class AddPlayersTeamUdf implements UDF1<String, String>{
    @AutowiredBroadcast
    private Broadcast<PlayersConfig> broadcast;

    @Override
    public String call(String player) throws Exception {
        Map<String, List<String>> playersMap = broadcast.getValue().playersMap;
        for (Map.Entry<String, List<String>> entry : playersMap.entrySet()) {
            if (entry.getValue().contains(player)) {
                return entry.getKey();
            }

        }
        return "";
    }
}
