package io.github;

import io.github.logit.Logistic;

import java.io.FileNotFoundException;

public class SampleClient {


    public static void main(String[] args) {

        Logistic logistic = new Logistic(5);

        try {
            logistic.train(logistic.readDataSet("dataset.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int[] testInstance = {2,1,1,0,1};
        System.out.println("prob(1|testInstance) = " + logistic.classify(testInstance));

        int[] testInstance2 = {1,0,1,0,0};
        System.out.println("prob(1|testInstance2) = " + logistic.classify(testInstance2));
    }
}
