
import org.apache.spark.SparkConf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
public class DevConf {

    @Bean
    public SparkConf sparkConf(){
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("songs");
        sparkConf.setMaster("local[*]");
        sparkConf.set("spark.driver.host", "localhost");

        return sparkConf;
    }
}

