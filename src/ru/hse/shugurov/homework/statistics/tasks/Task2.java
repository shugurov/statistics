package ru.hse.shugurov.homework.statistics.tasks;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;
import ru.hse.shugurov.homework.statistics.utils.BubbleSort;
import ru.hse.shugurov.homework.statistics.utils.CSVWriterForSortingInformation;
import ru.hse.shugurov.homework.statistics.utils.SelectionSort;
import ru.hse.shugurov.homework.statistics.utils.SortingAlgorithm;

import java.io.FileNotFoundException;
import java.io.IOException;
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
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("Не удалось создать файл");
            return;
        }
        int[] numberOfStepsForSelectionSort = task2.evaluate(new SelectionSort());
        int[] umberOfStepsForBubbleSort = task2.evaluate(new BubbleSort());
    }

    private int[] evaluate(SortingAlgorithm sortingAlgorithm) throws IOException
    {
        int[] numberOfSteps = new int[NUMBER_OF_EXPERIMENTS];
        sort(numberOfSteps, sortingAlgorithm);

        double[] doubleCopyOfNumberOfSteps = new double[NUMBER_OF_EXPERIMENTS];
        for (int i = 0; i < NUMBER_OF_EXPERIMENTS; i++)
        {
            doubleCopyOfNumberOfSteps[i] = numberOfSteps[i];
        }

        Mean meanEvaluator = new Mean();
        double mean = meanEvaluator.evaluate(doubleCopyOfNumberOfSteps);

        StandardDeviation standardDeviationEvaluator = new StandardDeviation(true);
        double standardDeviation = standardDeviationEvaluator.evaluate(doubleCopyOfNumberOfSteps);

        Percentile percentileEvaluator = new Percentile();
        double percentile5 = percentileEvaluator.evaluate(doubleCopyOfNumberOfSteps, 5);
        double percentile95 = percentileEvaluator.evaluate(doubleCopyOfNumberOfSteps, 95);

        csvWriter.appendToFile("report.csv", numberOfSteps, mean, standardDeviation, percentile5, percentile95);
        return numberOfSteps;
    }

    private void sort(int[] stepsArray, SortingAlgorithm algorithm)
    {
        for (int experimentNumber = 0; experimentNumber < NUMBER_OF_EXPERIMENTS; experimentNumber++)
        {
            double[] arrayToBeSorted = generateArray(ARRAY_LENGTH);
            stepsArray[experimentNumber] = algorithm.sort(arrayToBeSorted);
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

    public double evaluatePercentile(double[] array, double p)
    {
        Percentile percentile = new Percentile(p);
        return percentile.evaluate(array);
    }
}
