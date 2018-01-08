package football;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.SQLContext;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
@PropertySources({
        @PropertySource("classpath:user.properties"),
        @PropertySource("classpath:codes.properties"),
        @PropertySource("classpath:football_columns.properties"),
        @PropertySource("classpath:teams.properties"),
})
public class Conf {

    @Autowired
    private SparkConf sparkConf;

    @Bean
    public JavaSparkContext sc() {
        return new JavaSparkContext(sparkConf);
    }

    @Bean
    public SQLContext sqlContext(JavaSparkContext sc) {

        return new SQLContext(sc);
    }

}