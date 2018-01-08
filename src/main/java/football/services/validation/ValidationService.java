package football.services.validation;

import org.apache.spark.sql.DataFrame;

public interface ValidationService {
    public DataFrame validate(DataFrame dataFrame);
}
