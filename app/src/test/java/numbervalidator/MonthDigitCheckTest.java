package numbervalidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** 
 * Tests for MonthDigitCheck, that is only used by 
 * Nummer of type Organisationsnummer 
 */
public class MonthDigitCheckTest {
    @Test 
    void checkValidONs() {
        MonthDigitCheck check = new MonthDigitCheck();

        assertTrue(check.monthDigitIsAtLeast20(new Organisationsnummer("556614-3185")));
        assertTrue(check.monthDigitIsAtLeast20(new Organisationsnummer("16556601-6399")));
        assertTrue(check.monthDigitIsAtLeast20(new Organisationsnummer("262000-1111")));
        assertTrue(check.monthDigitIsAtLeast20(new Organisationsnummer("857202-7566")));
        assertTrue(check.monthDigitIsAtLeast20(new Organisationsnummer("16556614-3185")));
        assertTrue(check.monthDigitIsAtLeast20(new Organisationsnummer("16262000-1111")));
        assertTrue(check.monthDigitIsAtLeast20(new Organisationsnummer("16857202-7566")));
    }

    @Test 
    void checkONsWithWrongMonthDigits() {
        MonthDigitCheck check = new MonthDigitCheck();

        assertFalse(check.monthDigitIsAtLeast20(new Organisationsnummer("551614-3185")));
        assertFalse(check.monthDigitIsAtLeast20(new Organisationsnummer("16551601-6399")));
        assertFalse(check.monthDigitIsAtLeast20(new Organisationsnummer("261000-1111")));
        assertFalse(check.monthDigitIsAtLeast20(new Organisationsnummer("851202-7566")));
        assertFalse(check.monthDigitIsAtLeast20(new Organisationsnummer("16551614-3185")));
        assertFalse(check.monthDigitIsAtLeast20(new Organisationsnummer("16261000-1111")));
        assertFalse(check.monthDigitIsAtLeast20(new Organisationsnummer("16851202-7566")));
    }

    @Test 
    void checkEmptyON() {
        MonthDigitCheck check = new MonthDigitCheck();
        assertFalse(check.monthDigitIsAtLeast20(new Organisationsnummer("")));
    }
}
