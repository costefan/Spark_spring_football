package football.validators.impl;

import football.aop.ShowDfInTheBeginning;
import football.aop.ShowDfInTheEnd;
import football.udfs.validation.CodeFilterUdf;
import football.validators.Validator;
import org.apache.spark.sql.DataFrame;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Service
public class CodesValidator implements Validator {

    @Override
    @ShowDfInTheBeginning
    @ShowDfInTheEnd
    public DataFrame filter(DataFrame dataFrame) {
        return dataFrame.filter(callUDF(CodeFilterUdf.class.getName(),
                col("code"),
                col("from"),
                col("to")));
    }
}
