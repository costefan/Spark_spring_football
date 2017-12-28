package football;

import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Profile(Constants.DEV)
@Configuration
public class DevConf {

    @Bean
    public SparkConf sparkConf(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("football-app");
        sparkConf.setMaster("local[*]");

        return sparkConf;
    }
}
