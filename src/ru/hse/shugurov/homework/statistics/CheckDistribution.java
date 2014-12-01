package ru.hse.shugurov.homework.statistics;

import org.apache.commons.math3.distribution.TDistribution;

/**
 * Created by Иван on 02.12.2014.
 */
public class CheckDistribution
{
    public double lol(int degreesOfFreedom, double significance)
    {
        TDistribution distribution = new TDistribution(degreesOfFreedom);
        return distribution.inverseCumulativeProbability(1 - significance / 2);
    }
}
