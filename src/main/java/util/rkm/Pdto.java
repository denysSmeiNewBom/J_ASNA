package util.rkm;

import java.util.List;

public class Pdto {
    private List<Double> T;
    private List<Double> Y;
    private double[] yi;

    public Pdto(List<Double> t, List<Double> y) {
        T = t;
        Y = y;
    }

    public double[] getYi() {
        return yi;
    }

    public void setYi(double[] yi) {
        this.yi = yi;
    }

    public List<Double> getT() {
        return T;
    }

    public void setT(List<Double> t) {
        T = t;
    }

    public List<Double> getY() {
        return Y;
    }

    public void setY(List<Double> y) {
        Y = y;
    }
}
