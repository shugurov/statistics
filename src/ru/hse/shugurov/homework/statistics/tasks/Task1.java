package ru.hse.shugurov.homework.statistics.tasks;

import org.apache.commons.math3.distribution.TDistribution;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import ru.hse.shugurov.homework.statistics.model.ConfidenceInterval;
import ru.hse.shugurov.homework.statistics.utils.CSVWriterForConfidenceIntervals;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Ivan Shugurov on 02.12.2014.
 */
public class Task1
{
    private static final Random random = new Random();
    private static final double UNIFORM_EXPECTED_VALUE = 3;
    public static final double NORMAL_EXPECTED_VALUE = 0;
    private final CSVWriterForConfidenceIntervals csvWriter = new CSVWriterForConfidenceIntervals();
    private static final Task1 task1 = new Task1();
    private static final int ARRAY_LENGTH = 10;


    public static void main(String[] args)
    {
        try
        {
            evaluate(ARRAY_LENGTH, 1, true);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Ну удалось выполнить для 11 эксперимента с равномерным распределением");
        }
        try
        {
            evaluate(ARRAY_LENGTH, 400, true);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Ну удалось выполнить для 400 экспериментов c равномерным распределением");
        }
        try
        {
            evaluate(ARRAY_LENGTH, 400, false);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Ну удалось выполнить для 400 экспериментов c нормальным распределением");
        }
        try
        {
            evaluate(ARRAY_LENGTH, 1000, true);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Ну удалось выполнить для 1000 экспериментов c равномерным распределением");
        }
        try
        {
            evaluate(ARRAY_LENGTH, 1000, false);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Ну удалось выполнить для 1000 экспериментов c нормальным распределением");
        }
    }

    private static void evaluate(int arrayLength, int numberOfExperiments, boolean uniformlyDistributedArray) throws IOException
    {
        String fileName;
        if (uniformlyDistributedArray)
        {
            fileName = numberOfExperiments + "_experiments_uniform_distribution.csv";
        }
        else
        {
            fileName = numberOfExperiments + "_experiments_normal_distribution.csv";
        }
        task1.csvWriter.createFile(fileName, arrayLength);
        for (int i = 0; i < numberOfExperiments; i++)
        {
            double[] array;
            if (uniformlyDistributedArray)
            {
                array = task1.generateArrayFromRange(arrayLength);
            }
            else
            {
                array = task1.generateNormallyDistributedArray(arrayLength);
            }
            ConfidenceInterval confidenceInterval = task1.countConfidenceIntervalForExpectedValueUsingTDistribution(array, 0.1);
            boolean expectedValueWithinConfidenceInterval;
            if (uniformlyDistributedArray)
            {
                expectedValueWithinConfidenceInterval = confidenceInterval.isValueWithinInterval(UNIFORM_EXPECTED_VALUE);
            }
            else
            {
                expectedValueWithinConfidenceInterval = confidenceInterval.isValueWithinInterval(NORMAL_EXPECTED_VALUE);
            }
            task1.csvWriter.appendToFile(fileName, array, confidenceInterval, expectedValueWithinConfidenceInterval);
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

    private double[] generateNormallyDistributedArray(int arrayLength)
    {
        double[] array = new double[arrayLength];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = random.nextGaussian();
        }
        return array;
    }

    public ConfidenceInterval countConfidenceIntervalForExpectedValueUsingTDistribution(double[] array, double significance)
    {
        double mean = assesMeanValue(array);
        double standardDeviation = assesStandardDeviation(array, mean);
        TDistribution distribution = new TDistribution(array.length - 1);
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
