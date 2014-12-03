package ru.hse.shugurov.homework.statistics.utils;

import junit.framework.Assert;
import org.junit.Test;
import ru.hse.shugurov.homework.statistics.tasks.Task2;

import java.util.Arrays;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Ivan Shugurov on 03.12.2014.
 */
public class SortingTest
{
    private final BubbleSort bubbleSort = new BubbleSort();
    private final SelectionSort selectionSort = new SelectionSort();

    private boolean checkOrder(double[] array)
    {
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] < array[i - 1])
            {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testSortNotCaringAboutNumberOfSteps() throws Exception
    {
        Task2 task2 = new Task2();
        for (int i = 0; i < 50; i++)
        {
            System.out.println("array " + (i + 1));
            double[] array = task2.generateArray(50);
            double[] arrayCopy = Arrays.copyOf(array, array.length);

            bubbleSort.sort(array);
            selectionSort.sort(arrayCopy);

            boolean bubbleSortCorrect = checkOrder(array);
            boolean selectionSortCorrect = checkOrder(arrayCopy);

            assertTrue(array.toString(),bubbleSortCorrect);
            Assert.assertTrue(selectionSortCorrect);
        }
    }
}
