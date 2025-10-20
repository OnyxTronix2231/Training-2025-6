package L4.training;

public class FRC_Teams {

    public enum TeamName {
        Orbit,
        OnyxTronix,
        BumbleB,
        GreenBlitz,
        Demacia
    }

    public static int teamNum(TeamName teamName) {
        switch (teamName) {
            case Orbit:
                System.out.println("Blue");
                return 1690;
            case OnyxTronix:
                System.out.println("Red");
                return 2231;
            case BumbleB:
                System.out.println("Yellow");
                return 3339;
            case GreenBlitz:
                System.out.println("Green");
                return 4590;
            case Demacia:
                System.out.println("Purple");
                return 5635;
            default:
                return 0;

        }

    }
}
