package ru.hse.shugurov.homework.statistics.tasks;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import ru.hse.shugurov.homework.statistics.model.ConfidenceInterval;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class Task1Test
{

    private Task1 task1 = new Task1();

    private void testMean(double[] array, double expectedResult) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Method method = getMethod("assesMeanValue",double[].class);
        double mean = (Double) method.invoke(task1, array);
        assertEquals(expectedResult, mean, 0.00000000001);
    }

    private void testStandardDeviation(double[] array, double mean, double expectedResult)  throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        Method method = getMethod("assesStandardDeviation", double[].class, double.class);
        double standardDeviation = (Double) method.invoke(task1, array, mean);
        assertEquals(expectedResult, standardDeviation, 0.00000000001);
    }

    private Method getMethod(String methodName, Class... arguments) throws NoSuchMethodException
    {
        Method method = task1.getClass().getDeclaredMethod(methodName, arguments);
        method.setAccessible(true);
        return method;
    }

    @Test
    @Parameters(method = "provideArraysWithMeans")
    public void testMeanValue(double[] array, double expectedResult) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
        testMean(array, expectedResult);
    }

    @Test
    @Parameters(method = "provideArraysWithStandardDeviation")
    public void standardDeviation(double[] array, double expectedStandardDeviation, double mean) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException
    {
          testStandardDeviation(array, mean, expectedStandardDeviation);
    }

    @Test
    public void testConfidenceInterval()
    {
        double[] array = {1, 2, 3};
        ConfidenceInterval confidenceInterval = task1.countConfidenceIntervalForExpectedValueUsingTDistribution(array, 0.1);
        assertEquals(0.314, confidenceInterval.getLowerEndpoint(), 0.001);
        assertEquals(3.685, confidenceInterval.getUpperEndpoint(), 0.001);
    }

    public Object provideArraysWithMeans()
    {
        double[] array1 = new double[3];
        Arrays.fill(array1, 0);
        double mean1 = 0;

        double[] array2 = new double[3];
        array2[0] = 1;
        array2[1] = 2;
        array2[2] = 3;

        double[] array3 = new double[3];
        array3[0] = 0.1;
        array3[1] = 2.1;
        array3[2] = 3;

        return new Object[]{new Object[]{array1, mean1}, new Object[]{array2, 2}, new Object[]{array3, 1.7333333333333}};
    }

    public Object provideArraysWithStandardDeviation()
    {
        double[] array1 = new double[3];
        Arrays.fill(array1, 0);
        double standardDeviation1 = 0;

        double[] array2 = new double[3];
        array2[0] = 1;
        array2[1] = 2;
        array2[2] = 3;

        return new Object[]{new Object[]{array1, standardDeviation1, 0}, new Object[]{array2, 1, 2}};
    }
}