package football;

import football.mutators.impl.DescriptionMutator;
import football.services.DataFrameBuilder;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.DataFrame;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Conf.class)
@ActiveProfiles(Constants.QA)
public class DescriptionMutatorTest {
    @Autowired
    private DescriptionMutator descriptionMutator;

    @Autowired
    private DataFrameBuilder dataFrameBuilder;

    @Test
    public void buildDataFrame() throws Exception {

        DataFrame df = dataFrameBuilder.create("data/test/testData.txt");
        Assert.assertNotNull(df);
        String[] expectedColumns = new String[]{"code", "from", "to", "eventTime", "stadion"};
        Assert.assertTrue(Arrays.equals(df.columns(), expectedColumns));
    }

    @Test
    public void mutate() throws Exception {

        DataFrame df = dataFrameBuilder.create("data/rawData.txt");
        df = descriptionMutator.mutate(df);
        String[] expectedColumns = new String[]{"code", "from", "to", "eventTime", "stadion", "description"};
        Assert.assertTrue(Arrays.equals(df.columns(), expectedColumns));
    }

}



