package ru.hse.shugurov.homework.statistics.utils;

/**
 * Created by Ivan on 03.12.2014.
 */
public class SelectionSort implements SortingAlgorithm
{
    @Override
    public int sort(double[] array)
    {
        int numberOfSteps = 0;

        numberOfSteps++; // i = 0;
        numberOfSteps++; //array.length - 1
        numberOfSteps++; //i < array.length - 1
        for (int i = 0; i < array.length - 1; i++)
        {
            int min = i;
            numberOfSteps++;

            numberOfSteps++; // i+1
            numberOfSteps++; // j = i+ 1
            numberOfSteps++; // j < array.length
            for (int j = i + 1; j < array.length; j++)
            {
                numberOfSteps++; //  array[j]
                numberOfSteps++; //  array[min]
                numberOfSteps++; //  array[j] < array[min]
                if (array[j] < array[min])
                {
                    min = j;
                    numberOfSteps++;
                }

                // j++ equals j = j +1
                numberOfSteps++; // j + 1
                numberOfSteps++; // j = j +1

                numberOfSteps++; // j < array.length
            }
            numberOfSteps++; //array[i]
            numberOfSteps++; //double temp = array[i];
            double temp = array[i];

            numberOfSteps++; // array[min]
            numberOfSteps++; // array[i]
            numberOfSteps++; // array[i] = array[min]
            array[i] = array[min];

            numberOfSteps++; //array[min]
            numberOfSteps++; //array[min] = temp
            array[min] = temp;

            //i++ equals i = i + 1
            numberOfSteps++; // i + 1
            numberOfSteps++; //  i = i + 1

            numberOfSteps++; // array.length - 1
            numberOfSteps++; // i < array.length - 1
        }
        return numberOfSteps;//TODO
    }

    @Override
    public String getSortingAlgorithmName()
    {
        return "Selection";
    }
}
