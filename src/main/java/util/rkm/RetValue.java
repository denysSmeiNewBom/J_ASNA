package util.rkm;

import java.util.Arrays;

public class RetValue {
        private double t;
        private double[] yi;

        public RetValue(double t, double[] yi) {
            this.t = t;
            this.yi = Arrays.copyOf(yi, yi.length);
        }

        public void setYi(double[] yi) {
            this.yi = Arrays.copyOf(yi, yi.length);
            ;
        }

        @Override
        public String toString() {
            return "RetValue{" +
                    "t=" + t +
                    ", yi=" + Arrays.toString(yi) +
                    '}';
        }
    }