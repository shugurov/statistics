package ru.hse.shugurov.homework.statistics;

/**
 * Created by Ivan Shugurov on 02.12.2014.
 */
public class ConfidenceInterval
{
    private final double lowerEndpoint;
    private final double upperEndpoint;

    public ConfidenceInterval(double lowerEndpoint, double upperEndpoint)
    {
        if (upperEndpoint < lowerEndpoint)
        {
            throw new IllegalArgumentException();
        }
        this.lowerEndpoint = lowerEndpoint;
        this.upperEndpoint = upperEndpoint;
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


}
