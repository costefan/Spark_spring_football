package football.services.mutation;

import org.apache.spark.sql.DataFrame;

public interface MutatorService {
    public DataFrame run(DataFrame df);
}
