package football.mutators;

import org.apache.spark.sql.DataFrame;

public interface Mutator {
    public DataFrame mutate(DataFrame df);
}
