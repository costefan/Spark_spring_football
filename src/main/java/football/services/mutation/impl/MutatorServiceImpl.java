package football.services.mutation.impl;

import football.mutators.Mutator;
import football.services.mutation.MutatorService;
import football.validators.Validator;
import org.apache.spark.sql.DataFrame;
import org.apache.spark.storage.StorageLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Modifier;
import java.util.List;

@Service
public class MutatorServiceImpl implements MutatorService {
    private List<Mutator> mutators;

    @Autowired
    public MutatorServiceImpl(List<Mutator> mutators) {
        this.mutators = mutators;
    }

    @Override
    public DataFrame run(DataFrame df) {
        for (Mutator mutator: mutators) {
            if (!Modifier.isAbstract(mutator.getClass().getModifiers())) {

                df = mutator.mutate(df);

            }
        }
        df.persist(StorageLevel.MEMORY_AND_DISK());

        return df;
    }
}
