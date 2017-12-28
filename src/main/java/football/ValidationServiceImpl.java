package football;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ValidationServiceImpl implements ValidationService {
    private List<Validator> validators;

    @Autowired
    private JavaSparkContext sc;

    @Autowired
    public ValidationServiceImpl(List<Validator> validators) {
        this.validators = validators;
    }

    @Override
    public void validate(DataFrame dataFrame) {
        System.out.println("AAAAAAAA");
        System.out.println(validators);
        Set<Integer> invalidRows = new HashSet<Integer>();

        for (Validator validator: validators) {
            if (!Modifier.isAbstract(validator.getClass().getModifiers())) {
                invalidRows.addAll(validator.getInvalidRows());
            }
        }
    }
}
