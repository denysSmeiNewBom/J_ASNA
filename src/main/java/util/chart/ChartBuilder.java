package util.chart;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;

public class ChartBuilder extends Application {
    static Double[] arrX;
    static Double[] arrY;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Line Chart Sample");
        //defining the axes
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        //xAxis.setLabel("Number of Month");
        final LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Функція готовності");
        XYChart.Series series = new XYChart.Series();
        series.setName("Стан");
        //populating the series with data
        for (int i = 0; i < arrX.length; i++) {
            series.getData().add(new XYChart.Data(arrX[i], arrY[i]));
        }
        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void buildChart(double[] x, double[] y) {
        arrX = new Double[x.length];
        arrY = new Double[y.length];
        for (int i = 0; i < x.length; i++) {
            arrX[i] = x[i];
            arrY[i] = y[i];
        }
        launch(null);
    }

    public static void buildChart(List<Double> x, List<Double> y) {
        arrX = new Double[x.size()];
        arrY = new Double[y.size()];
        for (int i = 0; i < x.size(); i++) {
            arrX[i] = x.get(i);
            arrY[i] = y.get(i);
        }
        launch(null);
    }
}
