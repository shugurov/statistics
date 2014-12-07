package ru.hse.shugurov.homework.statistics.model;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class ConfidenceIntervalTest
{

    @Test
    @Parameters(method = "provideIntervals")
    public void testIsValueWithinInterval(double lowerBound, double upperBound, double value, boolean expecteResult) throws Exception
    {
        ConfidenceInterval confidenceInterval = new ConfidenceInterval(lowerBound, upperBound, 0, 0);
        boolean within = confidenceInterval.isValueWithinInterval(value);
        assertEquals(expecteResult, within);
    }

    private Object[] provideIntervals()
    {
        return new Object[]{new Object[]{1, 2, 0.5, false}, new Object[]{1, 2, 1, true}, new Object[]{1, 2, 2, true}, new Object[]{1, 4, 2, true}, new Object[]{1, 10, 12, false}};
    }
}