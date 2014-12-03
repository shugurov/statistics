package ru.hse.shugurov.homework.statistics.tasks;

import java.util.Random;

/**
 * Created by Ivan Shugurov on 03.12.2014.
 */
public class Task2
{
    private static final Random random = new Random();
    public static void main(String[] args)
    {

    }

    public double[] generateArray(int arrayLength)
    {
        double[] array = new double[arrayLength];
        for (int i = 0; i < arrayLength; i++)
        {
            array[i] = random.nextDouble();
        }
        return array;
    }
}
