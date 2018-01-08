package football.udfs.validation;

import football.AutowiredBroadcast;
import football.broadcasted_configs.UserConfig;
import football.udfs.RegisterUdf;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.sql.api.java.UDF1;

import java.io.Serializable;
import java.util.regex.Matcher;

@RegisterUdf
public class TimeFilterUdf implements UDF1<String, Boolean>, Serializable{

    @AutowiredBroadcast
    private Broadcast<UserConfig> userConfigBroadcast;

    @Override
    public Boolean call(String s) throws Exception {

        Matcher m  = userConfigBroadcast.getValue().getTimePattern().matcher(s);
        // if column name is valid and string is valid
        if (m.matches()) {
            int hours = Integer.parseInt(m.group(1));
            int min = Integer.parseInt(m.group(2));
            if (hours < userConfigBroadcast.getValue().getMaxPlayHours())
                return true;
            else if (hours == userConfigBroadcast.getValue().getMaxPlayHours()
                    && min == userConfigBroadcast.getValue().getMinMinutesAfterLastTime())
                return true;
            return false;
        } else {
            return false;
        }

    }
}
