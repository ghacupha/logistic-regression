package io.github;

import io.github.logit.Logistic;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LogisticTest {

    private Logistic logistic = new Logistic(5);

    @Test
    public void classify() throws Exception {

        logistic.train(logistic.readDataSet("dataset.txt"));

        int[] x = {2,1,1,0,1};
        int[] x2 = {1,0,1,0,0};

        assertEquals("prob(1|x) = ",0.01625696732212268,logistic.classify(x),0);
        assertEquals("prob(1|x2) = ",0.2490843963119936,logistic.classify(x2),0);
    }

    @Test
    public void theChancesOfOccurrenceAreEqualIfUntrained() throws Exception {

        //logistic skips training

        int[] x = {2,1,1,0,1};
        int[] x2 = {1,0,1,0,0};

        assertEquals("prob(1|x) = ",0.5,logistic.classify(x),0);
        assertEquals("prob(1|x2) = ",0.5,logistic.classify(x2),0);
    }




    @Test
    public void readDataSet() throws Exception {

        assertEquals(288,logistic.readDataSet("dataset.txt").size());
    }
}