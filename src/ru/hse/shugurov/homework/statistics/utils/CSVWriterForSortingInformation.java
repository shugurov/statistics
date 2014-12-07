package ru.hse.shugurov.homework.statistics.utils;

import java.io.*;

/**
 * Created by Ivan Shugurov on 03.12.2014.
 */
public class CSVWriterForSortingInformation
{

    private static final String SEPARATOR = ";";

    public CSVWriterForSortingInformation()
    {

    }

    public void createFile(String fileName, int arraySize) throws FileNotFoundException
    {
        File file = new File(fileName);
        try (PrintWriter printWriter = new PrintWriter(file))
        {
            for (int i = 1; i <= arraySize; i++)
            {
                printWriter.print("Experiment " + i);
                printWriter.print(SEPARATOR);
            }

            printWriter.print("Mean");
            printWriter.print(SEPARATOR);

            printWriter.print("Standard deviation");

            printWriter.println();
            printWriter.flush();
        }
    }

    public void appendToFile(String fileName, int[] numbersOfSteps, double mean, double standardDeviation) throws IOException
    {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true))))
        {
            for (double numberOfSteps : numbersOfSteps)
            {
                writer.print(numberOfSteps);
                writer.print(SEPARATOR);
            }

            writer.print(mean);
            writer.print(SEPARATOR);
            writer.print(standardDeviation);
            writer.print(SEPARATOR);

            writer.println();
            writer.flush();
        }
    }
}
