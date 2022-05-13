package util.rkm.addition;

public class AdditionK3 implements Addition {
    public double getAddition(int i, double[][] ki) {
        return (ONE_SIXTH * ki[0][i] + ONE_SIXTH * ki[1][i]);
    }
}