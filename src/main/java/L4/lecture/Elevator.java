package L4.lecture;

public class Elevator {

    public static int firstSolution(String company) {
        if (company.equals("Figma")) {
            return 10;
        } else {
            if (company.equals("Tableu")) {
                return 17;
            } else {
                if (company.equals("Solidworks")) {
                    return 23;
                } else {
                    if (company.equals("WPI")) {
                        return 30;
                    } else {
                        if (company.equals("monday")) {
                            return 32;
                        } else {
                            if (company.equals("spikeEssential")) {
                                return 38;
                            } else {
                                return 41;
                            }
                        }
                    }
                }
            }
        }
    }

    public static int secondSolution(String company) {
        switch (company) {
            case "Figma":
                return 10;
            case "Tableu":
                return 17;
            case "Solidworks":
                return 23;
            case "WPI":
                return 30;
            case "monday":
                return 32;
            case "spikeEssential":
                return 38;
            case "OnyxScout":
                return 41;
            default:
                return 0;
        }
    }

    public enum Company {
        Figma,
        Tableu,
        Solidworks,
        WPI,
        Monday,
        SpikeEssential,
        OnyxScout
    }

    public static int thirdSolution(Company company) {
        switch (company) {
            case Figma:
                return 10;
            case Tableu:
                return 17;
            case Solidworks:
                return 23;
            case WPI:
                return 30;
            case Monday:
                return 32;
            case SpikeEssential:
                return 38;
            case OnyxScout:
                return 41;
            default:
                return 0;
        }
    }
}
