package football;

import org.apache.spark.sql.DataFrame;

public interface ValidationService {
    public void validate(DataFrame dataFrame);
}
