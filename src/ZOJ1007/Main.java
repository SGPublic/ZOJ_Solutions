package ZOJ1007;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double x = new Scanner(System.in).nextDouble();
        System.out.printf("%.12f\n", get(x));
    }

    private static final double set = 2.0 * (20000 - 1.0) * (20000 - 1.0);
    private static double get(double x) {
        double ans = 0;
        for (int k = 1; k <= 20000; k++) {
            ans += 1.0 / (k * (k + x) * (k + 1));
        }
        ans *= 1.0 - x;
        ans += 1.0 + (1.0 - x) / set;
        return ans;
    }
}
