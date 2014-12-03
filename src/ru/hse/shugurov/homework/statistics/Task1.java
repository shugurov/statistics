package ru.hse.shugurov.homework.statistics;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Ivan Shugurov on 02.12.2014.
 */
public class Task1
{
    private static final Random random = new Random();
    private static final double EXPECTED_VALUE = 3;
    private final CSVWriter csvWriter = new CSVWriter();
    private static final Task1 task1 = new Task1();
    private static final int ARRAY_LENGTH = 10;


    public static void main(String[] args)
    {
        try
        {
            evaluate(ARRAY_LENGTH, 1);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Ну удалось выполнить для 10 экспериментов");
        }
        try
        {
            evaluate(ARRAY_LENGTH, 1000);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Ну удалось выполнить для 1000 экспериментов");
        }
    }

    private static void evaluate(int arrayLength, int numberOfExperiments) throws IOException
    {
        String fileName = numberOfExperiments + "_experiments.csv";
        task1.csvWriter.createNewCSVFile(fileName, arrayLength);
        for (int i = 0; i < numberOfExperiments; i++)
        {
            double[] array = task1.generateArrayFromRange(arrayLength);
            TDistribution tDistribution = new TDistribution(array.length - 1);
            ConfidenceInterval confidenceInterval = task1.countConfidenceIntervalForExpectedValueUsingTDistribution(tDistribution, array, 0.1);
            boolean expectedValueWithinConfidenceInterval = confidenceInterval.isValueWithinInterval(EXPECTED_VALUE);
            task1.csvWriter.appendToCSVFile(fileName, array, confidenceInterval, expectedValueWithinConfidenceInterval);
        }
    }


    private double[] generateArrayFromRange(int arrayLength)
    {
        double[] array = new double[arrayLength];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = random.nextDouble() * 6;
        }
        return array;
    }

    public ConfidenceInterval countConfidenceIntervalForExpectedValueUsingTDistribution(AbstractRealDistribution distribution, double[] array, double significance)
    {
        double mean = assesMeanValue(array);
        double standardDeviation = assesStandardDeviation(array, mean);
        double z = distribution.inverseCumulativeProbability(1 - significance / 2);
        double val = z * standardDeviation / Math.sqrt(array.length);
        double lowerEndpoint = mean - val;
        double upperEndpoint = mean + val;
        ConfidenceInterval confidenceInterval = new ConfidenceInterval(lowerEndpoint, upperEndpoint);
        return confidenceInterval;
    }

    private double assesStandardDeviation(double[] array, double mean)
    {
        StandardDeviation standardDeviation = new StandardDeviation(true);
        return standardDeviation.evaluate(array, mean);
    }

    private double assesMeanValue(double[] array)
    {
        Mean mean = new Mean();
        return mean.evaluate(array);
    }

}
