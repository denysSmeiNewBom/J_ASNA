package util.rkm;

import util.graph.State;
import util.rkm.addition.Addition;
import util.rkm.addition.AdditionK1;
import util.rkm.addition.AdditionK2;
import util.rkm.addition.AdditionK3;
import util.rkm.addition.AdditionK4;
import util.rkm.addition.AdditionK5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RKM {
    private static int SIZE_OF_MATRIX = 4;

    private static double t0 = 0;
    private static double T = 150;
    private static double eps = 1;
    private static double tao_0 = 0.1;
    private static int P = 5;

    private static final double ONE_SIXTH = 1.0 / 6.0;
    private static final double TWO_THIRD = 2.0 / 3.0;

    private static List<Addition> listOfAddition = new ArrayList<>();

    private List<State> graph;

    public RKM(List<State> graph) {
        this.graph = graph;
    }

    static {
        listOfAddition.add(new AdditionK1());
        listOfAddition.add(new AdditionK2());
        listOfAddition.add(new AdditionK3());
        listOfAddition.add(new AdditionK4());
        listOfAddition.add(new AdditionK5());
    }

    public Pdto calculateRCM() {
        Pdto pdto = new Pdto(new ArrayList<>(), new ArrayList<>());
        double[][] intensive = getMatrixOfIntensive(graph);
        SIZE_OF_MATRIX = intensive.length;
        double t = t0;
        double[] yi = getZeroYi();
        double[][] ki = new double[P][SIZE_OF_MATRIX];
        double[] ri;
        double tao = tao_0;
        double sumOfTime = 0;
        double sumPAndT = 0;
        int iter = 0;
        while (t < T) {
            do {
                ki = getKi(intensive, yi, ki, tao);
                ri = calculateR1(ki);

                if (decreaseStep(ri,yi)) {
                    tao /= 2;
                    continue;
                }
                break;
            } while (true);
            if (increaseStep(ri,yi)) {
                tao = tao * 2.0;
            }
            assignNewY1(ki, yi);

            t = t + tao;

            double sumOfGoodState = 0;
            for (int i = 0; i < yi.length; i++) {
                if (graph.get(i).getVector()[0] >= 10) {
                    sumOfGoodState += yi[i];
                }
            }
            sumOfTime += t;
            sumPAndT += t * sumOfGoodState;
            pdto.getT().add(t);
            pdto.getY().add(sumOfGoodState);
            System.out.println("iteration = " + iter++ + " t = " + t + "  sumOfGoodState = " + sumOfGoodState);
        }
        System.out.println();
        double sum = 0;
        for (int i = 0; i < yi.length; i++) {
            sum += yi[i];
            System.out.print("y" + i + " = " + yi[i] + "; ");
        }
        System.out.println("\n\nСередній час роботи = " + sumPAndT / sumOfTime);
        System.out.println("Сума всіх ймовірностей стану = " + sum);
        pdto.setYi(yi);
        return pdto;
    }

    private boolean decreaseStep(double[] ri,double[] yi) {
        for (int i = 0; i < ri.length; i++) {
            if (Math.abs(ri[i]) > eps * Math.abs(yi[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean increaseStep(double[] ri,double[] yi) {
        double oneThirdOfEps = eps / 32;
        for (int i = 0; i < ri.length; i++) {
            if (Math.abs(ri[i]) < (oneThirdOfEps * Math.abs(yi[i]))) {
                return true;
            }
        }
        return false;
    }

    private static double[] getZeroYi() {
        if (SIZE_OF_MATRIX < 1) {
            throw new RuntimeException("Size of matrix must be greater than 0");
        }
        double[] yi = new double[SIZE_OF_MATRIX];
        yi[0] = 1;
        return yi;
    }

    private double[] calculateR1(double ki[][]) {
        if (ki == null) return null;
        double[] ri = new double[ki[0].length];
        for (int i = 0; i < ki[0].length; i++) {
            ri[i] = ((2.0 * ki[0][i]) - (9.0 * ki[2][i]) + (8.0 * ki[3][i]) - ki[4][i]) / 30.0;
        }
        return ri;
    }

    private void assignNewY1(double ki[][], double[] yi) {
        for (int i = 0; i < ki[0].length; i++) {
            yi[i] = yi[i] + ONE_SIXTH * ki[0][i] + TWO_THIRD * ki[3][i] + ONE_SIXTH * ki[4][i];
        }
    }

    private double[][] getKi(double[][] coefficient, double[] yi, double[][] ki, double tao) {
        for (int i = 0; i < P; i++) {
            ki[i] = getKi(coefficient, yi, ki, tao, listOfAddition.get(i), i);
        }
        return ki;
    }

    private double[] getKi(double[][] coefficient, double[] yi, double[][] ki, double tao, Addition addition, int i) {
        for (int j = 0; j < yi.length; j++) {
            ki[i][j] = getFValueForI(j, coefficient, ki, yi, addition, tao);
        }
        return ki[i];
    }

    private double getFValueForI(int i, double[][] coefficient, double[][] ki, double[] yi, Addition addition, double tao) {
        double sum = (yi[0] + addition.getAddition(0, ki)) * coefficient[i][0];
        for (int j = 1; j < yi.length; j++) {
            sum += (yi[j] + addition.getAddition(j, ki)) * coefficient[i][j];
        }
        return sum * tao;
    }

    public static double[][] getMatrixOfIntensive(List<State> graph) {
        double[][] matrixOfIntensive = new double[graph.size()][graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            State currentState = graph.get(i);
            Map<Integer, Double> intensity = currentState.getIntensity();
            Set<Integer> keys = intensity.keySet();
            for (Integer k : keys) {
                matrixOfIntensive[k][i] = intensity.get(k);
            }
        }
        return matrixOfIntensive;
    }
}
