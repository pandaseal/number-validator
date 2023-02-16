package numbervalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ControlDigitCheckTest {

    @Test 
    void checkValidPNs() {
        ControlDigitCheck check = new ControlDigitCheck();
        assertTrue(check.isCorrectControlDigit(new Personnummer("900118+9811")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("201701102384")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("141206-2380")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("20080903-2386")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("7101169295")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("198107249289")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("19021214-9819")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("190910199827")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("191006089807")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("192109099180")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("4607137454")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("194510168885")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("189102279800")));
        assertTrue(check.isCorrectControlDigit(new Personnummer("189912299816")));
    }

    @Test 
    void checkValidONs() {
        ControlDigitCheck check = new ControlDigitCheck();
        assertTrue(check.isCorrectControlDigit(new Organisationsnummer("556614-3185")));
        assertTrue(check.isCorrectControlDigit(new Organisationsnummer("16556601-6399")));
        assertTrue(check.isCorrectControlDigit(new Organisationsnummer("262000-1111")));
        assertTrue(check.isCorrectControlDigit(new Organisationsnummer("857202-7566")));
        assertTrue(check.isCorrectControlDigit(new Organisationsnummer("16556614-3185")));
        assertTrue(check.isCorrectControlDigit(new Organisationsnummer("16262000-1111")));
        assertTrue(check.isCorrectControlDigit(new Organisationsnummer("16857202-7566")));
    }

    @Test 
    void checkValidSNs() {
        ControlDigitCheck check = new ControlDigitCheck();
        assertTrue(check.isCorrectControlDigit(new Samordningsnummer("190910799824")));
        assertTrue(check.isCorrectControlDigit(new Samordningsnummer("19091079-9824")));
        assertTrue(check.isCorrectControlDigit(new Samordningsnummer("0910799824")));
        assertTrue(check.isCorrectControlDigit(new Samordningsnummer("091079-9824")));
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