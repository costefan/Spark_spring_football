package football;

import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.List;

@Service
public class TimeValidator implements Validator {
    public final int MAX_PLAY_TIME = 120;
    @Override
    public DataFrame filter(DataFrame dataFrame) {
        return null;
    }
}
