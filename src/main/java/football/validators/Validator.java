package football.validators;

import org.apache.spark.sql.DataFrame;

import java.util.List;

public interface Validator {
    public DataFrame filter(DataFrame df);
}
