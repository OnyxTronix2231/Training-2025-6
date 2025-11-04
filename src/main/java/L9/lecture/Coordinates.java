package L9.lecture;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;

public class Coordinates {

    public static Translation2d getRobotFromCamera() {
        return new Translation2d();
    }

    public static void main(String[] args) {
        Translation2d aprilTagPosition = new Translation2d(0.5, 0.5);
        double apriltagAngle = 5;

        Translation2d robotRawLocation = getRobotFromCamera();
        Rotation2d angle = new Rotation2d(apriltagAngle);
        Translation2d rotated = robotRawLocation.rotateBy(angle);
        Translation2d trueLocation = aprilTagPosition.plus(rotated);
    }
}
