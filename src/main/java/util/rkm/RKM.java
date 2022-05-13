package util.rkm;


import com.sun.corba.se.impl.orbutil.graph.Graph;
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
    private static double T = 1;
    private static double eps = 0.000000000000005;
    private static double tao_0 = 0.00005;//step
    private static int P = 5;
    private static double[] y0i = getZeroYi();

    private static final double ONE_SIXTH = 1.0 / 6.0;
    private static final double TWO_THIRD = 2.0 / 3.0;

    private static List<Addition> listOfAddition = new ArrayList<>();

    private List<State> graph;

    public RKM(List<State> graph) {
        this.graph = graph;
    }

    public RKM() {
    }

    static {
        listOfAddition.add(new AdditionK1());
        listOfAddition.add(new AdditionK2());
        listOfAddition.add(new AdditionK3());
        listOfAddition.add(new AdditionK4());
        listOfAddition.add(new AdditionK5());
    }

    public static void main(String[] args) {
        RKM rkm = new RKM();
        rkm.calculateRCM();
    }

    public Pdto calculateRCM() {
        Pdto pdto = new Pdto(new ArrayList<>(), new ArrayList<>());
        double[][] intensive = getMatrixOfIntensive(graph);// розкоментовуєш graph і воно підставить матрицю інтенсивності графу
        //закоментовуєш graph і воно підставить одну з матриць 4x4
        SIZE_OF_MATRIX = intensive.length;
        double t = t0;
        double[] yi = getZeroYi();
        double[][] ki = new double[P][SIZE_OF_MATRIX];
        double[] ri;
        double tao = tao_0;
        int iter = 0;
        while (t < T) {
            do {
                ki = getKi(intensive, yi, ki, tao);
                ri = calculateR1(ki);

                if (decreaseStep(ri)) {
                    tao /= 2;
                    continue;
                }
                break;
            } while (true);
            if (increaseStep(ri)) {
                tao = tao * 2.0;
            }
            assignNewY1(ki, yi);

            t = t + tao;

            double sumOfGoodState = 0;
            for (int i = 0; i < yi.length; i++) {//сума пешок хороших станів
                if (graph.get(i).getVector()[0] >= 20){
                    sumOfGoodState += yi[i];
                }
            }
            pdto.getT().add(t);
            pdto.getY().add(sumOfGoodState);
            System.out.println("Iteration = " + iter++ + " t = " + t + "  sumOfGoodState = " + sumOfGoodState);
        }
        double sum = 0;
        for (int i = 0; i < yi.length; i++) {
            sum += yi[i];
            System.out.print("y" + i + " = " + yi[i] + "; ");
        }
        System.out.println("\n\nSum = " + sum);
        pdto.setYi(yi);
        return pdto;
    }

    private boolean decreaseStep(double[] ri) {
        for (int i = 0; i < ri.length; i++) {
            if (Math.abs(ri[i]) > eps) {
                return true;
            }
        }
        return false;
    }

    private boolean increaseStep(double[] ri) {
        for (int i = 0; i < ri.length; i++) {
            if (Math.abs(ri[i]) < (eps / 30)) {
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
        for (int i = 1; i < SIZE_OF_MATRIX; i++) {
            //yi[i] = 0.000000000000001;// тут я для всі y від 1-го до n-го (за виключенням 0-го) присвоюю їм це числдл
            // Бо там получаються нулі і значення всіх y окрім 0-го рівні 0
        }
        return yi;
    }

    private double[] calculateR1(double ki[][]) {
        if (ki == null) return null;
        double[] ri = new double[ki[0].length];
        for (int i = 0; i < ki[0].length; i++) {
            ri[i] = (-2.0 * ki[0][i] + 9.0 * ki[2][i] - 8.0 * ki[3][i] + ki[4][i]) / 30.0;
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

        double y = yi[0];
        double add = addition.getAddition(0, ki);
        double coef = coefficient[i][0];
        double sum = (y + add) * coef;
        for (int j = 1; j < yi.length; j++) {
            y = yi[j];
            add = addition.getAddition(j, ki);
            coef = coefficient[i][j];
            sum += (y + add) * coef;
        }
        return sum * tao;
    }

    private static final double l01 = 1;
    private static final double l02 = 2;
    private static final double l10 = 2;
    private static final double l13 = 2;
    private static final double l20 = 3;
    private static final double l23 = 1;
    private static final double l31 = 3;
    private static final double l32 = 2;

    /*public static double[][] getMatrixOfIntensive() {//матриця 4 на 4 для якої все працює. розкоме цю закоментуй нижню
        double[][] matrixOfIntensive = new double[SIZE_OF_MATRIX][SIZE_OF_MATRIX];
        matrixOfIntensive[0][0] = -(l01 + l02);
        matrixOfIntensive[0][1] = l10;
        matrixOfIntensive[0][2] = l20;
        matrixOfIntensive[1][0] = l01;
        matrixOfIntensive[1][1] = -(l10 + l13);
        matrixOfIntensive[1][3] = l31;
        matrixOfIntensive[2][0] = l02;
        matrixOfIntensive[2][2] = -(l20 + l23);
        matrixOfIntensive[2][3] = l32;
        matrixOfIntensive[3][1] = l13;
        matrixOfIntensive[3][2] = l23;
        matrixOfIntensive[3][3] = -(l31 + l32);
        return matrixOfIntensive;
    }*/

    public static double[][] getMatrixOfIntensive() {//матриця 4 на 4 вибрана з графу.  розкоме цю закоментуй верхню
        double[][] matrixOfIntensive = new double[SIZE_OF_MATRIX][SIZE_OF_MATRIX];
        matrixOfIntensive[0][0] = (2.1);
        matrixOfIntensive[0][1] = -2.1;
        matrixOfIntensive[1][1] = 2.42333333;
        matrixOfIntensive[1][2] = -2.09;
        matrixOfIntensive[1][1] = -(0.33333333);
        matrixOfIntensive[2][2] = 2.7466666666;;
        matrixOfIntensive[3][3] = (2.1);
        return matrixOfIntensive;
    }

    public static double[][] getMatrixOfIntensive(List<State> graph) {// підставити матрицю інтенсивності з графу. Передай в параметри граф
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