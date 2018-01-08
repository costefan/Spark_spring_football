package football.validators.impl;

import football.aop.ShowDfInTheBeginning;
import football.aop.ShowDfInTheEnd;
import football.udfs.validation.TimeFilterUdf;
import football.validators.Validator;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.functions;
import org.springframework.stereotype.Service;

import static org.apache.spark.sql.functions.callUDF;
import static org.apache.spark.sql.functions.col;

@Service
public class TimeValidator implements Validator {

    @Override
    @ShowDfInTheBeginning
    @ShowDfInTheEnd
    public DataFrame filter(DataFrame dataFrame) {
        return dataFrame.filter(functions.callUDF(TimeFilterUdf.class.getName(), col("eventTime")));
    }
}
