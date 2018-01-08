package football;

import org.apache.spark.sql.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {


    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Conf.class);

        FootballProcessing fProcess = context.getBean(FootballProcessing.class);
        DataFrame resultDataset = fProcess.run();
        resultDataset.show();
    }
}
