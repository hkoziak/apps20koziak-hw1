package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private final double ABS_ZERO = -273.0;
    private double avgTemp, devTemp, minTemp , maxTemp;

    public TempSummaryStatistics() {
        this.avgTemp = ABS_ZERO;
        this.devTemp = ABS_ZERO;
        this.maxTemp = ABS_ZERO;
        this.minTemp = ABS_ZERO;
    }
    public void UpdateData(TemperatureSeriesAnalysis tempAnalysis) {
        this.avgTemp = tempAnalysis.average();
        this.devTemp = tempAnalysis.deviation();
        this.maxTemp = tempAnalysis.max();
        this.minTemp = tempAnalysis.min();
    }

    public double getAvgTemp() {
        return avgTemp;
    }

    public double getDevTemp() {
        return devTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinTemp() {
        return minTemp;
    }
}