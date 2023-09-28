package util.chart;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SwingChartBuilder {
    public static void buildChart(List<Double> x, List<Double> y) {
        JFrame frame = new JFrame("Line Chart Sample");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JPanel chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                double maxY = y.stream().mapToDouble(Double::doubleValue).max().orElse(0);
                double maxX = x.stream().mapToDouble(Double::doubleValue).max().orElse(0);

                int width = getWidth();
                int height = getHeight();

                // Draw x-axis
                g.drawLine(50, height - 50, width - 50, height - 50);

                // Draw y-axis
                g.drawLine(50, height - 50, 50, 50);

                // Scale factor for x and y
                double xScale = (width - 100) / maxX;
                double yScale = (height - 100) / maxY;

                // Draw x-axis scale markings and labels
                for (int i = 1; i <= 10; i++) {
                    int xPos = (int) (50 + (i * maxX / 10) * xScale);
                    g.drawLine(xPos, height - 45, xPos, height - 55);
                    g.drawString(Integer.toString(i * (int) (maxX / 10)), xPos - 10, height - 30);
                }

                // Draw y-axis scale markings and labels
                for (double i = 0.1; i <= 1.0; i += 0.1) {
                    int yPos = (int) (height - 50 - i * maxY * yScale);
                    g.drawLine(45, yPos, 55, yPos);
                    g.drawString(String.format("%.1f", i), 20, yPos + 5);
                }

                // Draw data points and connect them
                g.setColor(Color.RED);
                for (int i = 0; i < x.size(); i++) {
                    int xPos = (int) (50 + x.get(i) * xScale);
                    int yPos = (int) (height - 50 - y.get(i) * yScale);
                    g.fillOval(xPos - 2, yPos - 2, 4, 4);

                    if (i > 0) {
                        int prevX = (int) (50 + x.get(i - 1) * xScale);
                        int prevY = (int) (height - 50 - y.get(i - 1) * yScale);
                        g.drawLine(prevX, prevY, xPos, yPos);
                    }
                }
            }
        };

        frame.add(chartPanel);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        // Example usage:
        List<Double> x = new ArrayList<>(Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0));
        List<Double> y = new ArrayList<>(Arrays.asList(0.2, 0.4, 0.1, 0.3, 0.5));

        SwingUtilities.invokeLater(() -> buildChart(x, y));
    }
}
