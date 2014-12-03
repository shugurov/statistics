package ru.hse.shugurov.homework.statistics.utils;

/**
 * Created by Ivan Shugurov on 03.12.2014.
 */
public class BubbleSort implements SortingAlhorithm
{
    @Override
    public int sort(double[] array)
    {
        int numberOfSteps = 0;

        numberOfSteps++; // i = 1
        numberOfSteps++; // i < array.length
        for (int i = 1; i < array.length; i++)
        {
            numberOfSteps++; // k = i
            numberOfSteps++; // k >= 1
            for (int k = i; k >= 1; k--)
            {
                numberOfSteps++; //k - 1
                numberOfSteps++; //array[k - 1]
                numberOfSteps++; //array[k]
                numberOfSteps++; //array[k - 1] > array[k]
                if (array[k - 1] > array[k])
                {
                    numberOfSteps++; // k - 1
                    numberOfSteps++; // array[k - 1]
                    numberOfSteps++; //temp = array[k - 1]
                    double temp = array[k - 1];

                    numberOfSteps++; //k - 1
                    numberOfSteps++; // array[k - 1]
                    numberOfSteps++; // array[k]
                    numberOfSteps++; //array[k - 1] = array[k]
                    array[k - 1] = array[k];

                    numberOfSteps++; // array[k]
                    numberOfSteps++; //
                    array[k] = temp; //array[k] = temp
                }

                //k-- equals k = k - 1
                numberOfSteps++; // k - 1
                numberOfSteps++; //  k = k - 1

                numberOfSteps++; // k >= 1
            }

            //i++ equals i = i + 1
            numberOfSteps++; // i + 1
            numberOfSteps++; //  i = i + 1

            numberOfSteps++; // i < array.length
        }
        return numberOfSteps;
    }

    @Override
    public String getSortingAlgorithmName()
    {
        return "Bubble";
    }
}
