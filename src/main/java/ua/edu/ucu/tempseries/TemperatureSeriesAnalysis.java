package ua.edu.ucu.tempseries;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private final double absZero = -273.0;
    private double[] tempSet;
    private int size = 0;

    public TemperatureSeriesAnalysis() {
        tempSet = new double[]{};
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        if ((checkIfAbsZero(temperatureSeries)) == 1) {
            throw new InputMismatchException();
        }
        tempSet = temperatureSeries;
        size = temperatureSeries.length;
    }

    private int checkIfAbsZero(double[] tempSeries) {
        for (double temp : tempSeries) {
            if (temp < absZero) {
                return 1;
            }
        }
        return 0;
    }

    private void checkTempArrayLength() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
    }

    public double average() {
        checkTempArrayLength();
        double tempSum = 0;
        for (int i = 0; i < size; ++i) {
            tempSum += tempSet[i];
        }
        return tempSum / size;
    }

    public double deviation() {
        checkTempArrayLength();
        double average = average();
        double sqVals = 0;
        for (int i = 0; i < size; ++i) {
            sqVals += java.lang.Math.pow((average - tempSet[i]), 2);
        }
        return java.lang.Math.sqrt(sqVals / size);
    }

    public double min() {
        checkTempArrayLength();
        double minVal = tempSet[0];
        for (int i = 0; i < size; ++i) {
            if (tempSet[i] < minVal) {
                minVal = tempSet[i];
            }
        }
        return minVal;
    }

    public double max() {
        checkTempArrayLength();
        double maxVal = tempSet[0];
        for (int i = 0; i < size; ++i) {
            if (tempSet[i] > maxVal) {
                maxVal = tempSet[i];
            }
        }
        return maxVal;
    }

    public double findTempClosestToZero() {
        return 0;
    }

    public double findTempClosestToValue(double tempValue) {
        return 0;
    }

    public double[] findTempsLessThen(double tempValue) {
        return null;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        return null;
    }

    public TempSummaryStatistics summaryStatistics() {
        return null;
    }

    public int addTemps(double... temps) {
        return 0;
    }
}
