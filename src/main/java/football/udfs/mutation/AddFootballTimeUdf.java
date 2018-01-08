package football.udfs.mutation;

import football.AutowiredBroadcast;
import football.broadcasted_configs.UserConfig;
import football.udfs.RegisterUdf;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;

import java.util.regex.Matcher;

@RegisterUdf
public class AddFootballTimeUdf implements UDF1<String, String> {

    @AutowiredBroadcast
    public Broadcast<UserConfig> userConfigBroadcast;

    @Override
    public String call(String time) throws Exception {
        UserConfig userConfig = userConfigBroadcast.getValue();
        Matcher m  = userConfig.getTimePattern().matcher(time);
        String footballTime = "additional football time";
        if (m.matches()) {
            int hours = Integer.parseInt(m.group(1));
            if (hours < userConfig.getMaxFirstTimeHours()) {
                footballTime = "1";
            } else if (hours < userConfig.getMaxSecondTimeHours()) {
                footballTime = "2";
            }
        }

        return footballTime;

    }
}
