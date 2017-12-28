package football;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.Row;

public interface FootballProcessing {
    public DataFrame run();
}
