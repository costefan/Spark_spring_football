package football.udfs.mutation;

import football.AutowiredBroadcast;
import football.broadcasted_configs.CodesConfig;
import football.broadcasted_configs.UserConfig;
import football.udfs.RegisterUdf;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;

import java.util.Map;

@RegisterUdf
public class AddDescriptionUdf implements UDF1<Integer, String> {

    @AutowiredBroadcast
    private Broadcast<CodesConfig> broadcast;

    @AutowiredBroadcast
    private Broadcast<UserConfig> userConfigBroadcast;

    @Override
    public String call(Integer code) throws Exception {
        Map<Integer, String> codesDescrMap =  broadcast.getValue().codesDescriptionsMap;

        if (!codesDescrMap.containsKey(code)) {
            return userConfigBroadcast.getValue().getEmptyDescription();
        }

        return codesDescrMap.get(code);
    }
}
