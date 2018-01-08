package football.udfs;

import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.api.java.UDF1;
import org.apache.spark.sql.api.java.UDF2;
import org.apache.spark.sql.api.java.UDF3;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.util.*;

@Component
public class UdfRegistratorApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private Map<Class<?>, DataType> dataTypesMap = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SQLContext sqlContext;

    private DataType getReturningDataType(Object udfObj) {
        ParameterizedType t = (ParameterizedType)  udfObj.getClass().getGenericInterfaces()[0];
        List<?> args = Arrays.asList(t.getActualTypeArguments());
        Class<?> clazz = (Class<?>) args.get(args.size() - 1);

        return dataTypesMap.get(clazz);
    }

    public void buildTypesMap() {
        dataTypesMap.put(Integer.class, DataTypes.IntegerType);
        dataTypesMap.put(String.class, DataTypes.StringType);
        dataTypesMap.put(Boolean.class, DataTypes.BooleanType);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        buildTypesMap();

        Collection<Object> udfObjects = applicationContext.getBeansWithAnnotation(RegisterUdf.class).values();

        for(Object udfObj: udfObjects) {
            String simpleName = udfObj.getClass().getInterfaces()[0].getSimpleName();
            if (simpleName.equals("UDF1"))
                sqlContext.udf().register(udfObj.getClass().getName(), (UDF1) udfObj, getReturningDataType(udfObj));
            else if (simpleName.equals("UDF3"))
                sqlContext.udf().register(udfObj.getClass().getName(), (UDF3) udfObj, getReturningDataType(udfObj));
        }

    }
}
