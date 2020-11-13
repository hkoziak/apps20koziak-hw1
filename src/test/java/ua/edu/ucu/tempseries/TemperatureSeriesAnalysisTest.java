package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.InputMismatchException;


public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCallOnEmptyArray() {
        new TemperatureSeriesAnalysis().max();
    }

    @Test(expected = InputMismatchException.class)
    public void testWithValuesBelowAbsZero() {
        double[] temperatureSeries = {14.0, 02.0, 18.0, -272, -273.5};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testDeviation() {
        double expectedValue = 113.4823087;
        double[] temperatureSeries = {14.0, 02.0, 18.0, -272, 0, 98, 116, -83};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualValue = seriesAnalysis.deviation();
        assertEquals(expectedValue, actualValue, 0.00001);
    }

    @Test
    public void testMinValue() {
        double expectedValue = -272;
        double[] temperatureSeries = {14.0, 02.0, 18.0, -272, 0, 98, 116, -83};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualValue = seriesAnalysis.min();
        assertEquals(expectedValue, actualValue, 0.00001);
    }

    @Test
    public void testMaxValue() {
        double expectedValue = 116;
        double[] temperatureSeries = {14.0, 02.0, 18.0, -272, 0, 98, 116, -83};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualValue = seriesAnalysis.max();
        assertEquals(expectedValue, actualValue, 0.00001);
    }

    @Test
    public void testValueClosestToZero() {
        double expectedValue = 2;
        double[] temperatureSeries = {14.0, 02.0, 18.0, -272, 98, 116, -83};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualValue = seriesAnalysis.findTempClosestToZero();
        assertEquals(expectedValue, actualValue, 0.00001);
    }

    @Test
    public void testTwoSameValuesClosestToZero() {
        double expectedValue = 2;
        double[] temperatureSeries = {14.0, 02.0, -2.0, 18.0, -272, 98, 116, -83};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualValue = seriesAnalysis.findTempClosestToZero();
        assertEquals(expectedValue, actualValue, 0.00001);
    }

    @Test
    public void testClosestToValue() {
        double expectedValue = 14;
        double[] temperatureSeries = {14.0, 02.0, -2.0, 18.0, -272, 0, 98, 116, -83};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double actualValue = seriesAnalysis.findTempClosestToValue(15);
        assertEquals(expectedValue, actualValue, 0.00001);
    }

    @Test
    public void testTempValuesLessThan() {
        double[] expectedValue = {-2.0, -272, -83};
        double[] temperatureSeries = {14.0, 02.0, -2.0, 18.0, -272, 0, 98, 116, -83};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualValue = seriesAnalysis.findTempsLessThen(0);
        assertArrayEquals(expectedValue, actualValue, 0.0001);
    }

    @Test
    public void testTempValuesGreaterThan() {
        double[] expectedValue = {116};
        double[] temperatureSeries = {14.0, 02.0, -2.0, 18.0, -272, 0, 98, 116, -83};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] actualValue = seriesAnalysis.findTempsGreaterThen(98);
        assertArrayEquals(expectedValue, actualValue, 0.0001);
    }

    @Test
    public void testTempSummaryStats() {
        double expectedAverage = 3.2;
        double expectedDeviation = 3.969886648;
        double expectedMin = -1;
        double expectedMax = 10;
        double[] temperatureSeries = {-1, 0, 2, 5, 10};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics actualValue = seriesAnalysis.summaryStatistics();

        double[] arr_exp_val = new double[]{expectedAverage, expectedDeviation, expectedMin, expectedMax};
        double[] arr_act_val = new double[]{actualValue.getAvgTemp(), actualValue.getDevTemp(),
                actualValue.getMinTemp(), actualValue.getMaxTemp()};

        assertArrayEquals(arr_exp_val, arr_act_val, 0.0001);
    }

    @Test
    public void testEmptyArraysAddTemps() {
        double expectedValue = 1;
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double[] temps = {3};
        double actualValue = seriesAnalysis.addTemps(temps);
        assertEquals(expectedValue, actualValue, 0.0001);
    }

    @Test
    public void testAddTemps() {
        double expectedValue = 2;
        double[] temperatureSeries = {-1, 0, 2, 5, 10};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        double[] temps = {3, 7};
        double actualValue = seriesAnalysis.addTemps(temps);
        assertEquals(expectedValue, actualValue, 0.0001);
    }
}
