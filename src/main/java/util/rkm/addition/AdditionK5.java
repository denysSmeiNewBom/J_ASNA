package util.rkm.addition;

public class AdditionK5 implements Addition {
    public double getAddition(int i, double[][] ki) {
        return (ONE_SECOND * ki[0][i] - THREE_SECOND * ki[2][i] + 2 * ki[3][i]);
    }
}