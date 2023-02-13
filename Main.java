public class Main {
    private static boolean consoleMode = false;

    public static void main(String[] args) {
        if (consoleMode) {
            runFromConsole(args);
        } else {
            /* checks example numbers */
            testPersonnummer();
            testOrganisationsnummer();
            testSamordningsnummer();
        }
    }

    /**
     * Runs with arguments from console. One number at a time is passed (as a string) and 
     * its validity is checked. 
     * The number is validated depending on the provided numberType (p, s or o). 
     * The result is printed out to the console.
     * If v or verbose is passed, the failing tests will be printed out after validation.
     * @param args input form console
     */
    private static void runFromConsole(String[] args) {
        boolean verbose = false;
        String numberType = "none";
        String inNumber = "none";

        for (int i = 0; i < args.length; ++i) {
            String param = args[i];
    
            if (param.equalsIgnoreCase("help") || param.equalsIgnoreCase("h")) {
                printHelpHint();
                return;
            } else if (param.equalsIgnoreCase("verbose") || param.equalsIgnoreCase("v")) {
                verbose = true;
            } else if (param.equalsIgnoreCase("personnummer") || param.equalsIgnoreCase("p")) {
                numberType = "p";
            } else if (param.equalsIgnoreCase("organisationsnummer") || param.equalsIgnoreCase("o")) {
                numberType = "o";
            } else if (param.equalsIgnoreCase("samordningsnummer") || param.equalsIgnoreCase("s")) {
                numberType = "s";
            } else {
                inNumber = param;
            }
        }
        if (inNumber == "none") {
            System.err.println("Error: no number to test provided. Run 'java Main help' for instructions.");
            return;
        }
        if (numberType == "none") {
            System.err.println("Error: no numberType provided. Run 'java Main help' for instructions.");
            return;
        }

        Nummer validatedNummer = validateNummer(inNumber, numberType);
        validatedNummer.printValidity(verbose);
    }

    private static void printHelpHint() {
        System.out.println("To test a number, run: 'java Main <number> <numberType>'");
        System.out.println("Available numberTypes are: 'personnummer'/'p', ");
        System.out.println("'organisationsnummer'/'o' or 'samordningsnummer'/'s'.");
        System.out.println("Optional: Add 'v'/'verbose' to get the failing tests printed out.");
    }

    /**
     * Test the validity of a list of numbers of the same type and make 
     * a pretty print with the results and failing tests.
     * @param message line to be printed before the results of the tests
     * @param numberType type of number ('p', 'o' or 's')
     * @param listOfNumbers list of numbers to test
     */
    public static void testNumbers(String message, String numberType, String[] listOfNumbers) {
        System.out.println(message);
        for (String inNumber : listOfNumbers) {
            Nummer validatedNummer = validateNummer(inNumber, numberType);
            validatedNummer.printValidity(true);
        }
        System.out.println("------------------------------");
    }

    /**
     * Test the validity a single nummer and get result and failing tests printed to console.
     * @param inNumber number to test
     * @param numberType type of number to test ('p', 'o' or 's')
     */
    public static void testNumber(String inNumber, String numberType) {
        Nummer validatedNummer = validateNummer(inNumber, numberType);
        validatedNummer.printValidity(true);
    }

    /**
     * Simple test of number, that returns true or false.
     * If you want to know what tests failed, use testNummer() that prints the results to console or 
     * validateNummer() that returns a Nummer object with all info.
     * @param inNumber number to test
     * @param numberType type of number to test ('p', 'o' or 's')
     * @return true if number is valid, else false
     */
    public static boolean isValidNummer(String inNumber, String numberType) {
        Nummer validatedNummer = validateNummer(inNumber, numberType);
        return validatedNummer.isValid;
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

    /**
     * Creates a Nummer object and validates it with ValidityChecks corresponding to the passed numberType.
     * @param inNumber number to test
     * @param numberType type of number ('p', 'o' or 's')
     * @return a Nummer object with validation info that can be used however needed
     */
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
}
