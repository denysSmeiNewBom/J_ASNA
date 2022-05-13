package util.rkm.addition;

public interface Addition {
    double ONE_SECOND = 1.0 / 2.0;
    double ONE_THIRD = 1.0 / 3.0;
    double ONE_SIXTH = 1.0 / 6.0;
    double ONE_EIGHTH = 1.0 / 8.0;
    double THREE_SECOND = 3.0 / 2.0;
    double THREE_EIGHTH = 3.0 / 8.0;

    double getAddition(int i, double[][] ki);
}