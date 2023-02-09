public class Main {

    public static void main(String[] args) {
        testProvidedNumbers();
    }

    public static void testProvidedNumbers() {
        String[] validPNs = {
            "900118+9811", 
            "201701102384", 
            "141206-2380", 
            "20080903-2386",
            "7101169295",
            "198107249289",
            "19021214-9819",
            "190910199827",
            "191006089807",
            "192109099180",
            "4607137454",
            "194510168885",
            "900118+9811",
            "189102279800",
            "189912299816"
        };

        String[] invalidPNs = {
            "201701272394",
            "190302299813"
        };

        System.out.println("Testing VALID personnummers:");
        for (String inNumber : validPNs) {
            Nummer validatedNummer = validateNummer(inNumber, "p");
            validatedNummer.printValidity();
        }
        System.out.println("------------------------------");

        System.out.println("Testing INVALID personnummers:");
        for (String inNumber : invalidPNs) {
            Nummer validatedNummer = validateNummer(inNumber, "p");
            validatedNummer.printValidity();
        }
        System.out.println("------------------------------");
    }

    public static Nummer validateNummer(String inNumber, String numberType) {
        Nummer nummer;
        if (numberType.equalsIgnoreCase("person") || numberType.equalsIgnoreCase("p")) {
            nummer = new Personnummer(inNumber);
        } else if (numberType.equalsIgnoreCase("organisations") || numberType.equalsIgnoreCase("o")) {
            nummer = new Organisationsnummer(inNumber);
        } else if (numberType.equalsIgnoreCase("sammordnings") || numberType.equalsIgnoreCase("s")) {
            nummer = new Sammordningsnummer(inNumber);
        } else {
            throw new IllegalArgumentException("Does not recognize numberType, should be \"p\", \"o\" or \"s\".");
        }

        nummer.checkValidity();
        return nummer;
    }

    public static Nummer validateNummer(Nummer nummer) {
        nummer.checkValidity();
        return nummer;
    }
}
