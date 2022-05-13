package util.rkm.addition;

public class AdditionK4 implements Addition {
    public double getAddition(int i, double[][] ki) {
        return (ONE_EIGHTH * ki[0][i] + THREE_EIGHTH * ki[2][i]);
    }
}