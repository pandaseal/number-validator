public class Main {

    public static void main(String[] args) {
        //testSamordningsnummer();
        //testPersonnummer();
        //testOrganisationsnummer();

        testNumber(" 900118+9811 ", "p");
    }

    private static void testNumbers(String message, String numberType, String[] listOfNumbers) {
        System.out.println(message);
        for (String inNumber : listOfNumbers) {
            Nummer validatedNummer = validateNummer(inNumber, numberType);
            validatedNummer.printValidity();
        }
        System.out.println("------------------------------");
    }

    public static void testNumber(String inNumber, String numberType) {
        Nummer validatedNummer = validateNummer(inNumber, numberType);
        validatedNummer.printValidity();
    }

    public static void testOrganisationsnummer() {
        String[] validONs = {
            "556614-3185",
            "16556601-6399",
            "262000-1111",
            "857202-7566",
            "16556614-3185",
            "16262000-1111",
            "16857202-7566",
        };
        String[] invalidONs = {
            "17556614-3185",
            "1816556601-6399",
            "19262000-1111",
            "20857202-7566"
        };
        testNumbers("Testing VALID organisationsnummer:", "o", validONs);
        testNumbers("Testing INVALID organisationsnummer:", "o", invalidONs);

    }

    public static void testSamordningsnummer() {
        String[] validSNs = {
            "190910799824",
            "19091079-9824",
            "0910799824",
            "091079-9824",
        };
        String[] invalidSNs = {
            "190910799825"
        };
        testNumbers("Testing VALID samordningsnummer:", "s", validSNs);
        testNumbers("Testing INVALID samordningsnummer:", "s", invalidSNs);
    }

    public static void testPersonnummer() {
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
        testNumbers("Testing VALID personnummer:", "p", validPNs);
        testNumbers("Testing INVALID personnummer:", "p", invalidPNs);
    }

    public static Nummer validateNummer(String inNumber, String numberType) {
        Nummer nummer;
        if (numberType.equalsIgnoreCase("personnummer") || numberType.equalsIgnoreCase("p")) {
            nummer = new Personnummer(inNumber);
        } else if (numberType.equalsIgnoreCase("organisationsnummer") || numberType.equalsIgnoreCase("o")) {
            nummer = new Organisationsnummer(inNumber);
        } else if (numberType.equalsIgnoreCase("samordningsnummer") || numberType.equalsIgnoreCase("s")) {
            nummer = new Samordningsnummer(inNumber);
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
