package L4.lecture;

public class First {
    public enum TeamName {
        Orbit,
        OnyxTronix,
        BumbleB,
        GreenBlitz,
        Demacia;
    }
        public static int numbers (TeamName teamName){
        switch (teamName) {
            case Orbit:
                System.out.println("blue");
                return 1690;
            case OnyxTronix:
                System.out.println("red");
                return 2231;
            case BumbleB:
                System.out.println("yellow");
                return 3339;
            case GreenBlitz:
                System.out.println("green");
                return 4590;
            case Demacia:
                System.out.println("purple");
                return 5635;
            default:
                return 0;
        }
    }

}

