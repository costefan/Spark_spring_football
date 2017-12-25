import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.rdd.RDD;

public class Main {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("Football app");
        sparkConf.setMaster("local[*]");

        SparkContext sc = new SparkContext(sparkConf);
        RDD<String> rdd = sc.textFile("data/rawData.txt", 1);
    }
}
