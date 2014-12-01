package ru.hse.shugurov.homework.statistics;

import org.apache.commons.math3.distribution.AbstractRealDistribution;
import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.util.Random;

/**
 * Created by Ivan Shugurov on 02.12.2014.
 */
public class Task1
{
    private static final Random random = new Random();


    private double[] generateArrayFromRange(int arrayLength)
    {
        double[] array = new double[arrayLength];
        for (int i = 0; i < array.length; i++)
        {
            array[i] = random.nextDouble() * 6;
        }
        return array;
    }

    private ConfidenceInterval countConfidenceIntervalForExpectedValueUsingTDistribution(AbstractRealDistribution distribution, double[] array, double significance)
    {
        double mean = assesMeanValue(array);
        double standardDeviation = assesStandardDeviation(array, mean);
        //AbstractRealDistribution distribution = new TDistribution(array.length);
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
