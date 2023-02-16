package numbervalidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/*
 * Test for PlusUsageCheck, a ValidityCheck used by 
 * Nummer of type Personnummer and Samordningsnummer.
 */
public class PlusUsageCheckTest {
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
    String[] validSNs = {
        "190910799824",
        "19091079-9824",
        "0910799824",
        "091079-9824",
    };
    @Test 
    void checkValidPNs() {
        PlusUsageCheck check = new PlusUsageCheck();
        assertTrue(check.isCorrectUseOfPlus(new Personnummer("900118+9811")));
        assertTrue(check.isCorrectUseOfPlus(new Personnummer("201701102384")));
        assertTrue(check.isCorrectUseOfPlus(new Personnummer("141206-2380")));
        assertTrue(check.isCorrectUseOfPlus(new Personnummer("189102279800")));
        assertTrue(check.isCorrectUseOfPlus(new Personnummer("189912299816")));
    }

    @Test 
    void checkValidSNs() {
        PlusUsageCheck check = new PlusUsageCheck();
        assertTrue(check.isCorrectUseOfPlus(new Samordningsnummer("190910799824")));
        assertTrue(check.isCorrectUseOfPlus(new Samordningsnummer("19091079-9824")));
        assertTrue(check.isCorrectUseOfPlus(new Samordningsnummer("0910799824")));
        assertTrue(check.isCorrectUseOfPlus(new Samordningsnummer("091079-9824")));
    }
    @Test 
    void checkPNsWithWrongShape() {
        PlusUsageCheck check = new PlusUsageCheck();
        assertFalse(check.isCorrectUseOfPlus(new Personnummer("900118+-9811")));
        assertFalse(check.isCorrectUseOfPlus(new Personnummer("2017 01102384")));
        assertFalse(check.isCorrectUseOfPlus(new Personnummer("141206- 2380")));
        assertFalse(check.isCorrectUseOfPlus(new Personnummer("18 9102279800")));
        assertFalse(check.isCorrectUseOfPlus(new Personnummer("1899122998168")));
    }

    @Test 
    void checkValidSNsWithWrongShape() {
        PlusUsageCheck check = new PlusUsageCheck();
        assertFalse(check.isCorrectUseOfPlus(new Samordningsnummer("19 0910799824")));
        assertFalse(check.isCorrectUseOfPlus(new Samordningsnummer("19091079-+9824")));
        assertFalse(check.isCorrectUseOfPlus(new Samordningsnummer("091 0799824")));
        assertFalse(check.isCorrectUseOfPlus(new Samordningsnummer("09 1079-9824")));
    }
    
    @Test
    void hasNotPlusAnd8digitsPNTest() {
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Personnummer("900118+9811")));
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Personnummer("201701102384")));
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Personnummer("141206-2380")));
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Personnummer("189102279800")));
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Personnummer("189912299816")));
    }

    @Test
    void hasPlusAnd8digitsPNTest() {
        assertTrue(PlusUsageCheck.hasPlusAnd8digits(new Personnummer("18900118+9811")));
        assertTrue(PlusUsageCheck.hasPlusAnd8digits(new Personnummer("18910227+9800")));
        assertTrue(PlusUsageCheck.hasPlusAnd8digits(new Personnummer("18991229+9816")));
    }

    @Test
    void hasNotPlusAnd8digitsSNTest() {
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Samordningsnummer("190910799824")));
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Samordningsnummer("19091079-9824")));
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Samordningsnummer("141206-2380")));
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Samordningsnummer("0910799824")));
        assertFalse(PlusUsageCheck.hasPlusAnd8digits(new Samordningsnummer("091079-9824")));
    }

    @Test
    void hasPlusAnd8digitsSNTest() {
        assertTrue(PlusUsageCheck.hasPlusAnd8digits(new Samordningsnummer("19091079+9824")));
        assertTrue(PlusUsageCheck.hasPlusAnd8digits(new Samordningsnummer("18141206+2380")));
        assertTrue(PlusUsageCheck.hasPlusAnd8digits(new Samordningsnummer("20091079+9824")));
        assertTrue(PlusUsageCheck.hasPlusAnd8digits(new Samordningsnummer("18091079+9824")));
    }

    @Test
    void isHundredPlusPNTest() {
        PlusUsageCheck check = new PlusUsageCheck();
        assertTrue(check.has12DigitsAndIsHundredPlus(new Personnummer("18900118+9811")));
        assertTrue(check.has12DigitsAndIsHundredPlus(new Personnummer("189001189811")));
        
        assertFalse(check.has12DigitsAndIsHundredPlus(new Personnummer("900118+9811")));
        assertFalse(check.has12DigitsAndIsHundredPlus(new Personnummer("201701102384")));
        assertFalse(check.has12DigitsAndIsHundredPlus(new Personnummer("141206-2380")));
        
        assertFalse(check.has12DigitsAndIsHundredPlus(new Personnummer("1412 06-2380")));
        assertFalse(check.has12DigitsAndIsHundredPlus(new Personnummer("1412906-2380")));
    }

    @Test
    void isHundredPlusSNTest() {
        PlusUsageCheck check = new PlusUsageCheck();
        assertTrue(check.has12DigitsAndIsHundredPlus(new Samordningsnummer("19091079+9824")));
        assertTrue(check.has12DigitsAndIsHundredPlus(new Samordningsnummer("18141206+2380")));
        assertTrue(check.has12DigitsAndIsHundredPlus(new Samordningsnummer("18091079+9824"))); 
        assertTrue(check.has12DigitsAndIsHundredPlus(new Samordningsnummer("19091079-9824")));
        assertTrue(check.has12DigitsAndIsHundredPlus(new Samordningsnummer("18141206-2380")));
        assertTrue(check.has12DigitsAndIsHundredPlus(new Samordningsnummer("18091079-9824")));
        
        assertFalse(check.has12DigitsAndIsHundredPlus(new Samordningsnummer("20091079+9824")));
    }
}
