package ru.hse.shugurov.homework.statistics.utils;

import ru.hse.shugurov.homework.statistics.model.ConfidenceInterval;

import java.io.*;

/**
 * Created by Ivan Shugurov on 03.12.2014.
 */
public class CSVWriterForConfidenceIntervals
{
    private static final String SEPARATOR = ";";

    public void createFile(String fileName, int numberOfElementsInArray) throws FileNotFoundException
    {
        File file = new File(fileName);
        if (file.exists())
        {
            file.delete();
        }
        try (PrintWriter writer = new PrintWriter(file))
        {
            for (int i = 0; i < numberOfElementsInArray; i++)
            {
                String headerColumn = String.format("array[%s]", (i + 1));
                writer.print(headerColumn);
                writer.print(SEPARATOR);
            }

            writer.write("Mean");
            writer.write(SEPARATOR);
            writer.write("Standard deviation");
            writer.write(SEPARATOR);

            writer.write("Lower endpoint of confidence interval");
            writer.write(SEPARATOR);
            writer.write("Upper endpoint of confidence interval");
            writer.write(SEPARATOR);
            writer.write("Real expected value within interval");
            writer.write(SEPARATOR);

            writer.println();
            writer.flush();
        }
    }

    public void appendToFile(String fileName, double[] array,ConfidenceInterval confidenceInterval, boolean expectedValueWithinInterval) throws IOException
    {
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName, true))))
        {
            for (double value : array)
            {
                writer.print(value);
                writer.print(SEPARATOR);
            }

            writer.print(confidenceInterval.getMean());
            writer.print(SEPARATOR);
            writer.print(confidenceInterval.getStandardDeviation());
            writer.print(SEPARATOR);

            writer.print(confidenceInterval.getLowerEndpoint());
            writer.print(SEPARATOR);
            writer.print(confidenceInterval.getUpperEndpoint());
            writer.print(SEPARATOR);

            if (expectedValueWithinInterval)
            {
                writer.write("1");
            }
            else
            {
                writer.write("0");
            }
            writer.println();
            writer.flush();
        }
    }
}
