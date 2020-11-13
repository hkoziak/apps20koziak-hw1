package ua.edu.ucu.tempseries;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private static final double ABS_ZERO = -273.0;
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
            if (temp < ABS_ZERO) {
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
            sqVals += (average - tempSet[i]) * (average - tempSet[i]);
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
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        checkTempArrayLength();
        double closest = tempSet[0];
        double diff = Math.abs(tempValue - closest);
        for (int i = 0; i < size; ++i) {
            if (java.lang.Math.abs(tempSet[i] - tempValue) < diff) {
                closest = tempSet[i];
                diff = Math.abs(tempSet[i] - tempValue);
            } else if
                ((java.lang.Math.abs(tempSet[i] - tempValue) == diff) &&
                            ((tempSet[i] - closest) > 0)) {
                closest = tempSet[i];
            }
        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        checkTempArrayLength();
        double[] smallerTemps = new double[size];
        int smTempslength = 0;
        for (int i = 0; i < size; ++i) {
            if (tempSet[i] < tempValue) {
                smallerTemps[smTempslength] = tempSet[i];
                ++smTempslength;
            }
        }
        double[] croppedSmallerTemps =
                new double[smTempslength];
        System.arraycopy(
                smallerTemps, 0, croppedSmallerTemps, 0, smTempslength);
        return croppedSmallerTemps;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        checkTempArrayLength();
        double[] greaterTemps = new double[size];
        int grTempslength = 0;
        for (int i = 0; i < size; ++i) {
            if (tempSet[i] > tempValue) {
                greaterTemps[grTempslength] = tempSet[i];
                ++grTempslength;
            }
        }
        double[] croppedGreaterTemps = new double[grTempslength];
        System.arraycopy(greaterTemps, 0, croppedGreaterTemps, 0, grTempslength);
        return croppedGreaterTemps;
    }

    public TempSummaryStatistics summaryStatistics() {
        TempSummaryStatistics statisticsAnalyzer =  new TempSummaryStatistics();
        checkTempArrayLength();
        statisticsAnalyzer.updateData(this);
        return statisticsAnalyzer;
    }

    public int addTemps(double... temps) {
        checkIfAbsZero(temps);
        int tempsSize = temps.length;
        if ((size + tempsSize) > tempSet.length) {
            double[] copySet = new double[size + tempsSize];
            System.arraycopy(tempSet, 0, copySet, 0, size);
            System.arraycopy(temps, 0, copySet, size, tempsSize);
            this.tempSet = copySet;
            this.size = size + tempsSize;
        } else if (size == 0) {
            this.tempSet = temps;
            this.size = tempsSize;
        }
        return size;
    }
}
