package ru.hse.shugurov.homework.statistics.tasks;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import ru.hse.shugurov.homework.statistics.utils.BubbleSort;
import ru.hse.shugurov.homework.statistics.utils.CSVWriterForSortingInformation;
import ru.hse.shugurov.homework.statistics.utils.SelectionSort;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Created by Ivan Shugurov on 03.12.2014.
 */
public class Task2
{
    private static final Random random = new Random();
    private static final int ARRAY_LENGTH = 20;
    private static final int NUMBER_OF_EXPERIMENTS = 100;
    private final CSVWriterForSortingInformation csvWriter = new CSVWriterForSortingInformation();

    public static void main(String[] args) throws IOException
    {
        Task2 task2 = new Task2();
        try
        {
            task2.csvWriter.createFile("report.csv", NUMBER_OF_EXPERIMENTS);
            task2.evaluate();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("Не удалось создать файл");
            return;
        }
    }

    private void evaluate() throws IOException
    {

        int[][] totalResult = new int[3][NUMBER_OF_EXPERIMENTS];
        sort(totalResult);
        for (int[] resultArray : totalResult)
        {
            double[] doubleCopyOfNumberOfSteps = new double[NUMBER_OF_EXPERIMENTS];
            for (int i = 0; i < NUMBER_OF_EXPERIMENTS; i++)
            {
                doubleCopyOfNumberOfSteps[i] = resultArray[i];
            }

            Mean meanEvaluator = new Mean();
            double mean = meanEvaluator.evaluate(doubleCopyOfNumberOfSteps);

            StandardDeviation standardDeviationEvaluator = new StandardDeviation(true);
            double standardDeviation = standardDeviationEvaluator.evaluate(doubleCopyOfNumberOfSteps);

            csvWriter.appendToFile("report.csv", resultArray, mean, standardDeviation);
        }

    }

    private void sort(int[][] stepsArray)
    {
        SelectionSort selectionSort = new SelectionSort();
        BubbleSort bubbleSort = new BubbleSort();
        for (int experimentNumber = 0; experimentNumber < NUMBER_OF_EXPERIMENTS; experimentNumber++)
        {
            double[] arrayToBeSorted = generateArray(ARRAY_LENGTH);
            double[] arrayToBeSorted2 = Arrays.copyOf(arrayToBeSorted, arrayToBeSorted.length);
            stepsArray[0][experimentNumber] = selectionSort.sort(arrayToBeSorted);
            stepsArray[1][experimentNumber] = bubbleSort.sort(arrayToBeSorted2);
            stepsArray[2][experimentNumber] = stepsArray[0][experimentNumber] - stepsArray[1][experimentNumber];
        }
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
