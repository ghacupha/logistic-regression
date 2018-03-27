package io.github.logit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Logistic {

    private static final Logger log = LoggerFactory.getLogger(Logistic.class);

    /** the learning learningRate */
    private final double learningRate;

    /** the weight to learn */
    private final double[] weights;

    /** the number of iterations */
    private final int ITERATIONS = 3000;

    public Logistic(int n) {
        this.learningRate = 0.0001;
        weights = new double[n];
    }

    private double sigmoid(double z) {
        return 1.0 / (1.0 + Math.exp(-z));
    }

    public void train(List<Instance> instances) {
        int n=0;
        while (n<ITERATIONS) {

            double lik = 0.0;

            for (Instance instance : instances) {

                int[] x = instance.getInstanceDataArray();

                double predicted = classify(x);

                int label = instance.getInstanceLabel();

                IntStream.range(0, weights.length)
                    .forEachOrdered(j -> weights[j] = weights[j] + learningRate * (label - predicted) * x[j]);

                // not necessary for learning
                lik += label * Math.log(classify(x)) + (1 - label) * Math.log(1 - classify(x));
            }

            log.debug("Iteration: {} {} mle: {}",n,Arrays.toString(weights),lik);
            n++;
        }
    }

    public double classify(int[] x) {
        double logit = .0;
        for (int i=0; i<weights.length;i++)  {
            logit += weights[i] * x[i];
        }
        return sigmoid(logit);
    }

    public List<Instance> readDataSet(String file) throws FileNotFoundException {

        List<Instance> instances = new ArrayList<>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(getClass().getClassLoader().getResource(file).getPath()));
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#")) {
                    continue;
                }
                String[] columns = line.split("\\s+");

                // skip first column and last column is the label
                int i = 1;
                int[] data = new int[columns.length-2];
                for (i=1; i<columns.length-1; i++) {
                    data[i-1] = Integer.parseInt(columns[i]);
                }
                int label = Integer.parseInt(columns[i]);
                Instance instance = new Instance(label, data);
                instances.add(instance);
            }
        } finally {
            if (scanner != null)
                scanner.close();
        }
        return instances;
    }

}