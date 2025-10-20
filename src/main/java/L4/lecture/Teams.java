package L4.lecture;

public class Teams {

    public enum FRCTeams {
        Orbit,
        OnyxTronix,
        BumbleB,
        GreenBlitz,
        Demacia
    }

    public static int getTeamNum(FRCTeams team) {
        switch (team) {
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

    public static void main(String[] args) {
        FRCTeams team = FRCTeams.OnyxTronix;
        System.out.println("the best team is " + getTeamNum(team));
    }
}
