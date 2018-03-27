[![Build Status](https://travis-ci.org/ghacupha/logistic-regression.svg?branch=master)](https://travis-ci.org/ghacupha/logistic-regression)

logistic-regression
===================

A simple implementation of logisitic regression in Java.

Simply by creating the "logistic" object which is initialized by providing the number of descriptor
variables.
The project trains the model by providing the data as a text file "dataset.txt".
The probability of occurrence of the instance is subsequently calculated as follows

```java
public class LogisticTest {

    private Logistic logistic = new Logistic(5);

    @Test
    public void classify() throws Exception {

        logistic.train(logistic.readDataSet("dataset.txt"));

        int[] x = {2,1,1,0,1};
        /*System.out.println("prob(1|x) = " + logistic.classify(testInstance));*/

        int[] x2 = {1,0,1,0,0};
        /*System.out.println("prob(1|x2) = " + logistic.classify(testInstance2));*/

        assertEquals("prob(1|x) = ",0.01625696732212268,logistic.classify(x),0);
        assertEquals("prob(1|x2) = ",0.2490843963119936,logistic.classify(x2),0);
    }

    @Test
    public void theChancesOfOccurrenceAreEqualIfUntrained() throws Exception {

        //logistic skips training

        int[] x = {2,1,1,0,1};
        /*System.out.println("prob(1|x) = " + logistic.classify(testInstance));*/

        int[] x2 = {1,0,1,0,0};
        /*System.out.println("prob(1|x2) = " + logistic.classify(testInstance2));*/

        assertEquals("prob(1|x) = ",0.5,logistic.classify(x),0);
        assertEquals("prob(1|x2) = ",0.5,logistic.classify(x2),0);
    }
}
```