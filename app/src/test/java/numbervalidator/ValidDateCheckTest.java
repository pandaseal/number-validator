package numbervalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for ValidDateCheck that is used for 
 * Nummer objects of type Personnummer and Samordningsnummer.
 */
public class ValidDateCheckTest {
    @Test
    void isValidDateCheckPNTest() {
        ValidDateCheck check = new ValidDateCheck();
        assertTrue(check.isValidDate(new Personnummer("900118+9811")));
        assertTrue(check.isValidDate(new Personnummer("201701102384")));
        assertTrue(check.isValidDate(new Personnummer("141206-2380")));
        assertTrue(check.isValidDate(new Personnummer("20080903-2386")));
        assertTrue(check.isValidDate(new Personnummer("7101169295")));
        assertTrue(check.isValidDate(new Personnummer("198107249289")));
        assertTrue(check.isValidDate(new Personnummer("19021214-9819")));
        assertTrue(check.isValidDate(new Personnummer("190910199827")));
        assertTrue(check.isValidDate(new Personnummer("191006089807")));
        assertTrue(check.isValidDate(new Personnummer("192109099180")));
        assertTrue(check.isValidDate(new Personnummer("4607137454")));
        assertTrue(check.isValidDate(new Personnummer("194510168885")));
        assertTrue(check.isValidDate(new Personnummer("189102279800")));
        assertTrue(check.isValidDate(new Personnummer("189912299816")));
    }
    
    @Test
    void isINValidDateCheckPNTest() {
        ValidDateCheck check = new ValidDateCheck();
        assertFalse(check.isValidDate(new Personnummer("190302299813")));
    }

    @Test
    void isValidDateCheckSNTest() {
        ValidDateCheck check = new ValidDateCheck();
        assertTrue(check.isValidDate(new Samordningsnummer("190910799824")));
        assertTrue(check.isValidDate(new Samordningsnummer("19091079-9824")));
        assertTrue(check.isValidDate(new Samordningsnummer("0910799824")));
        assertTrue(check.isValidDate(new Samordningsnummer("091079-9824")));
    }
    
    @Test
    void isINValidDateCheckSNTest() {
        ValidDateCheck check = new ValidDateCheck();
        assertFalse(check.isValidDate(new Samordningsnummer("")));
        /* Lack of examples of Samordningsnummer. */
    }

    @Test
    void getRealDateTest() {
        assertEquals("19091019", ValidDateCheck.getRealDate("19091079"));
        assertEquals("091019", ValidDateCheck.getRealDate("091079"));
        assertEquals("", ValidDateCheck.getRealDate(""));
    }

    @Test
    void dateExistsTest() {
        assertTrue(ValidDateCheck.dateExists("20230217", false));
        assertTrue(ValidDateCheck.dateExists("230217", false));
        assertTrue(ValidDateCheck.dateExists("900118", true));
        
        assertFalse(ValidDateCheck.dateExists("230229", false));
        assertFalse(ValidDateCheck.dateExists("19030229", false));
        assertFalse(ValidDateCheck.dateExists("", false));
        assertFalse(ValidDateCheck.dateExists("7", false));
        
        /* Special case: 2000 is a leap year, but 1900 is not */
        assertTrue(ValidDateCheck.dateExists("000229", false));
        assertFalse(ValidDateCheck.dateExists("000229", true));
    }

}
