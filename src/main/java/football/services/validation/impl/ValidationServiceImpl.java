package football.services.validation.impl;

import football.services.validation.ValidationService;
import football.validators.Validator;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.storage.StorageLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Modifier;
import java.util.List;

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
    public DataFrame validate(DataFrame dataFrame) {

        for (Validator validator: validators) {
            if (!Modifier.isAbstract(validator.getClass().getModifiers())) {

                dataFrame = validator.filter(dataFrame);

            }
        }
        dataFrame.persist(StorageLevel.MEMORY_AND_DISK());

        return dataFrame;
    }
}
