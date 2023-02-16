package numbervalidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** 
 * Tests for YearDigitCheck, that is only used by 
 * Nummer of type Organisationsnummer 
 */
public class YearDigitCheckTest {
    @Test 
    void checkValidONs() {
        YearDigitCheck check = new YearDigitCheck();

        assertTrue(check.yearDigitIs16(new Organisationsnummer("556614-3185")));
        assertTrue(check.yearDigitIs16(new Organisationsnummer("16556601-6399")));
        assertTrue(check.yearDigitIs16(new Organisationsnummer("262000-1111")));
        assertTrue(check.yearDigitIs16(new Organisationsnummer("857202-7566")));
        assertTrue(check.yearDigitIs16(new Organisationsnummer("16556614-3185")));
        assertTrue(check.yearDigitIs16(new Organisationsnummer("16262000-1111")));
        assertTrue(check.yearDigitIs16(new Organisationsnummer("16857202-7566")));
    }

    @Test 
    void checkONsWithWrongYearDigits() {
        YearDigitCheck check = new YearDigitCheck();

        assertFalse(check.yearDigitIs16(new Organisationsnummer("17556601-6399")));
        assertFalse(check.yearDigitIs16(new Organisationsnummer("18556614-3185")));
        assertFalse(check.yearDigitIs16(new Organisationsnummer("19262000-1111")));
        assertFalse(check.yearDigitIs16(new Organisationsnummer("20857202-7566")));
    }

    @Test 
    void checkEmptyON() {
        YearDigitCheck check = new YearDigitCheck();
        assertFalse(check.yearDigitIs16(new Organisationsnummer("")));
    }
}
