package ru.hse.shugurov.homework.statistics.model;

/**
 * Created by Ivan Shugurov on 02.12.2014.
 */
public class ConfidenceInterval
{
    private final double lowerEndpoint;
    private final double upperEndpoint;
    private final double mean;
    private final double standardDeviation;

    public ConfidenceInterval(double lowerEndpoint, double upperEndpoint, double mean, double standardDeviation)
    {
        this.standardDeviation = standardDeviation;
        if (upperEndpoint < lowerEndpoint)
        {
            throw new IllegalArgumentException();
        }
        this.lowerEndpoint = lowerEndpoint;
        this.upperEndpoint = upperEndpoint;
        this.mean = mean;
    }

    public double getUpperEndpoint()
    {
        return upperEndpoint;
    }

    public double getLowerEndpoint()
    {
        return lowerEndpoint;
    }

    public boolean isValueWithinInterval(double value)
    {
        if (value >= lowerEndpoint && value <= upperEndpoint)
        {
            return true;
        }
        else
        {
            return false;
        }
    }


    public double getMean()
    {
        return mean;
    }

    public double getStandardDeviation()
    {
        return standardDeviation;
    }
}
