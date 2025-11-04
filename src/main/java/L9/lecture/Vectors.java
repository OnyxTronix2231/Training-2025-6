package L9.lecture;

import edu.wpi.first.math.geometry.Translation2d;

public class Vectors {
    public static void main(String[] args) {
        Translation2d vector1 = new Translation2d(1, 2);
        Translation2d vector2 = new Translation2d(1, -1);

        Translation2d vector3 = vector1.plus(vector2);
        vector3 = vector3.times(3);
        System.out.println(vector3.getNorm());
        System.out.println(vector3.getAngle().getDegrees());
    }
}
