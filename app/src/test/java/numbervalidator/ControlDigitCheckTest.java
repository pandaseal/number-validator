package numbervalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ControlDigitCheckTest {

    @Test 
    void checkValidPNs() {
        // actually valid PNs
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
            "189102279800",
            "189912299816"
        };
        for (String validPNstr : validPNs) {
            ControlDigitCheck check = new ControlDigitCheck();
            Nummer validPN = new Personnummer(validPNstr);
            assertEquals(true, check.isCorrectControlDigit(validPN));
        }
    }

    @Test 
    void checkValidONs() {
        // actually valid PNs
        String[] validONs = {
            "556614-3185",
            "16556601-6399",
            "262000-1111",
            "857202-7566",
            "16556614-3185",
            "16262000-1111",
            "16857202-7566",
        };
        for (String validONstr : validONs) {
            ControlDigitCheck check = new ControlDigitCheck();
            Nummer validON = new Organisationsnummer(validONstr);
            assertEquals(true, check.isCorrectControlDigit(validON));
        }
    }

    @Test 
    void checkValidSNs() {
        // actually valid PNs
        String[] validSNs = {
            "190910799824",
            "19091079-9824",
            "0910799824",
            "091079-9824",
        };
        for (String validSNstr : validSNs) {
            ControlDigitCheck check = new ControlDigitCheck();
            Nummer validSN = new Organisationsnummer(validSNstr);
            assertEquals(true, check.isCorrectControlDigit(validSN));
        }
    }
    
    @Test 
    void checkPNsWithWrongControlDigits() {
        // valid PNs with control digit changed
        String[] testNumbers = {
            "201701272394", 
            "900118+9812", 
            "201701102385", 
            "141206-2381", 
            "20080903-2387",
            "7101169296",
            "198107249280",
            "19021214-9812",
            "190910199824",
            "191006089808",
            "192109099181",
            "4607137455",
            "194510168886",
            "189102279801",
            "189912299817"
        };
        for (String invalidPNstr : testNumbers) {
            ControlDigitCheck check = new ControlDigitCheck();
            Nummer invalidPN = new Personnummer(invalidPNstr);
            assertEquals(false, check.isCorrectControlDigit(invalidPN));
        }
    }

    @Test 
    void checkONsWithWrongControlDigits() {
        // valid PNs with control digit changed
        String[] testNumbers = {
            "556614-3186",
            "16556601-6390",
            "262000-1112",
            "857202-7567",
            "16556614-3186",
            "16262000-1112",
            "16857202-7568",
        };
        for (String invalidPNstr : testNumbers) {
            ControlDigitCheck check = new ControlDigitCheck();
            Nummer invalidPN = new Organisationsnummer(invalidPNstr);
            assertEquals(false, check.isCorrectControlDigit(invalidPN));
        }
    }

    @Test 
    void checkSNsWithWrongControlDigits() {
        // valid SNs with control digit changed
        String[] testNumbers = {
            "190910799825",
            "19091079-9825",
            "0910799827",
            "091079-9825",
        };
        for (String invalidNrStr : testNumbers) {
            ControlDigitCheck check = new ControlDigitCheck();
            Nummer invalidSN = new Samordningsnummer(invalidNrStr);
            assertEquals(false, check.isCorrectControlDigit(invalidSN));
        }
    }

    @Test 
    void checkEmptyPN() {
        ControlDigitCheck check = new ControlDigitCheck();
        Nummer emptyN = new Personnummer("");
        assertEquals(false, check.isCorrectControlDigit(emptyN));
    }

    @Test 
    void checkEmptySN() {
        ControlDigitCheck check = new ControlDigitCheck();
        Nummer emptyN = new Samordningsnummer("");
        assertEquals(false, check.isCorrectControlDigit(emptyN));
    }

    @Test 
    void checkEmptyON() {
        ControlDigitCheck check = new ControlDigitCheck();
        Nummer emptyN = new Organisationsnummer("");
        assertEquals(false, check.isCorrectControlDigit(emptyN));
    }

}