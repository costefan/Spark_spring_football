package football;

import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodesValidator implements Validator {

    @Override
    public DataFrame filter(DataFrame dataFrame) {
        return null;
    }
}
