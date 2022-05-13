package util.rkm.addition;

public class AdditionK2 implements Addition {
    public double getAddition(int i, double[][] ki) {
        return (ONE_THIRD * ki[0][i]);
    }
}