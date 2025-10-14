package L3.lecture;

import edu.wpi.first.math.MathUtil;

public class ClampUtils {

    public static int max(int a, int b) {
        if (a > b) {
            return a;
        } else {
            return b;
        }
    }

    public static int min(int a, int b) {
        if (a < b) {
            return a;
        } else {
            return b;
        }
    }

    public static int clamp(int val, int min, int max) {
        return max(min(val, max), min);
    }
}
