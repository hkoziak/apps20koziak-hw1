package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    private final double absZero = -273.0;
    private double avgTemp, devTemp, minTemp , maxTemp;

    public TempSummaryStatistics() {
        this.avgTemp = absZero;
        this.devTemp = absZero;
        this.maxTemp = absZero;
        this.minTemp = absZero;
    }
    public void updateData(TemperatureSeriesAnalysis tempAnalysis) {
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