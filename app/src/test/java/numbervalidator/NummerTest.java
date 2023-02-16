package numbervalidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NummerTest {
    @Test
    void getNumberOfDigitsOfPNTest() {
        assertEquals(10, new Personnummer("900118+9811").getNumberOfDigits());
        assertEquals(10, new Personnummer("4607137454").getNumberOfDigits());
        assertEquals(12, new Personnummer("201701102384").getNumberOfDigits());
        assertEquals(12, new Personnummer("20080903-2386").getNumberOfDigits());
    }

    @Test
    void getNumberOfDigitsOfONTest() {
        assertEquals(10, new Organisationsnummer("556614-3185").getNumberOfDigits());
        assertEquals(12, new Organisationsnummer("16556601-6399").getNumberOfDigits());
    }

    @Test
    void getNumberOfDigitsOfSNTest() {
        assertEquals(10, new Samordningsnummer("091079-9824").getNumberOfDigits());
        assertEquals(10, new Samordningsnummer("0910799824").getNumberOfDigits());
        assertEquals(12, new Samordningsnummer("19091079-9824").getNumberOfDigits());
        assertEquals(12, new Samordningsnummer("190910799824").getNumberOfDigits());
    }

    @Test
    void getNumberOfDigitsOfPNWithWrongShapeTest() {
        assertEquals(-2, new Personnummer("900118+98110").getNumberOfDigits());
        assertEquals(-2, new Personnummer("46071374540").getNumberOfDigits());
        assertEquals(-2, new Personnummer("2017011023840").getNumberOfDigits());
        assertEquals(-2, new Personnummer("200809030-2380").getNumberOfDigits());
    }

    @Test
    void getNumberOfDigitsOfONWithWrongShapeTest() {
        assertEquals(-2, new Organisationsnummer("5566140-3185").getNumberOfDigits());
        assertEquals(-2, new Organisationsnummer("16556601-63990").getNumberOfDigits());
    }

    @Test
    void getNumberOfDigitsOfSNWithWrongShapeTest() {
        assertEquals(-2, new Samordningsnummer("091079-98240").getNumberOfDigits());
        assertEquals(-2, new Samordningsnummer("0910790-9824").getNumberOfDigits());
        assertEquals(-2, new Samordningsnummer("09107998240").getNumberOfDigits());
        assertEquals(-2, new Samordningsnummer("19091079-98240").getNumberOfDigits());
        assertEquals(-2, new Samordningsnummer("190910790-9824").getNumberOfDigits());
        assertEquals(-2, new Samordningsnummer("19091079982400").getNumberOfDigits());
    }

    @Test
    void countNumberOfDigitsTest() {
        assertEquals(0, Nummer.countNumberOfDigits(""));
        assertEquals(0, Nummer.countNumberOfDigits("     "));
        assertEquals(1, Nummer.countNumberOfDigits("1"));
        assertEquals(2, Nummer.countNumberOfDigits("12"));
        assertEquals(0, Nummer.countNumberOfDigits("abc"));
        assertEquals(3, Nummer.countNumberOfDigits("abc123"));
        assertEquals(11, Nummer.countNumberOfDigits("091079-98240"));
        assertEquals(14, Nummer.countNumberOfDigits("19091079982400"));
        assertEquals(10, Nummer.countNumberOfDigits("900118+9811"));
        assertEquals(12, Nummer.countNumberOfDigits("20080903-2386"));
    }

    @Test
    void getMonthDigitFromPNTest() {
        assertEquals(1, new Personnummer("900118+9811").getMonthDigits());
        assertEquals(7, new Personnummer("4607137454").getMonthDigits());
        assertEquals(1, new Personnummer("201701102384").getMonthDigits());
        assertEquals(9, new Personnummer("20080903-2386").getMonthDigits());
    }

    @Test
    void getMonthDigitFromSNTest() {
        assertEquals(10, new Samordningsnummer("091079-9824").getMonthDigits());
        assertEquals(10, new Samordningsnummer("0910799824").getMonthDigits());
        assertEquals(10, new Samordningsnummer("19091079-9824").getMonthDigits());
        assertEquals(10, new Samordningsnummer("190910799824").getMonthDigits());
    }

    @Test
    void getMonthDigitFromONTest() {
        /* invalid ON */
        assertEquals(16, new Organisationsnummer("551614-3185").getMonthDigits());
        assertEquals(16, new Organisationsnummer("16551601-6399").getMonthDigits());
        assertEquals(10, new Organisationsnummer("261000-1111").getMonthDigits());
        assertEquals(12, new Organisationsnummer("851202-7566").getMonthDigits());
        assertEquals(16, new Organisationsnummer("16551614-3185").getMonthDigits());
        assertEquals(10, new Organisationsnummer("16261000-1111").getMonthDigits());
        assertEquals(12, new Organisationsnummer("16851202-7566").getMonthDigits());
        
        /* valid ON */
        assertEquals(66, new Organisationsnummer("556614-3185").getMonthDigits());
        assertEquals(66, new Organisationsnummer("16556601-6399").getMonthDigits());
        assertEquals(20, new Organisationsnummer("262000-1111").getMonthDigits());
        assertEquals(72, new Organisationsnummer("857202-7566").getMonthDigits());
        assertEquals(66, new Organisationsnummer("16556614-3185").getMonthDigits());
        assertEquals(20, new Organisationsnummer("16262000-1111").getMonthDigits());
        assertEquals(72, new Organisationsnummer("16857202-7566").getMonthDigits());
    }

    @Test
    void getMonthDigitFromPNWithWrongShapeTest() {
        assertEquals(-2, new Personnummer("900118+-9811").getMonthDigits());
        assertEquals(-2, new Personnummer("46071374 54").getMonthDigits());
        assertEquals(-2, new Personnummer("20170110238 4").getMonthDigits());
        assertEquals(-2, new Personnummer("200 80903-2386").getMonthDigits());
        assertEquals(-2, new Personnummer("").getMonthDigits());
    }

    @Test
    void getMonthDigitFromSNWithWrongShapeTest() {
        assertEquals(-2, new Samordningsnummer("091079-982").getMonthDigits());
        assertEquals(-2, new Samordningsnummer("091799824").getMonthDigits());
        assertEquals(-2, new Samordningsnummer("19091 079-9824").getMonthDigits());
        assertEquals(-2, new Samordningsnummer("1909107998248").getMonthDigits());
        assertEquals(-2, new Samordningsnummer("").getMonthDigits());
    }

    @Test
    void getMonthDigitFromONWithWrongShapeTest() {        
        assertEquals(-2, new Organisationsnummer("556614 -3185").getMonthDigits());
        assertEquals(-2, new Organisationsnummer("16556601-63990").getMonthDigits());
        assertEquals(-2, new Organisationsnummer("262000-111").getMonthDigits());
        assertEquals(-2, new Organisationsnummer("85720-7566").getMonthDigits());
        assertEquals(-2, new Organisationsnummer("16556614-31857").getMonthDigits());
        assertEquals(-2, new Organisationsnummer("12000-1111").getMonthDigits());
        assertEquals(-2, new Organisationsnummer("1685720 2-7566").getMonthDigits());
        assertEquals(-2, new Organisationsnummer("").getMonthDigits());
    }
    @Test
    void getYearDigitFromPNTest() {
        assertEquals(-1, new Personnummer("900118+9811").getYearDigits());
        assertEquals(-1, new Personnummer("4607137454").getYearDigits());
        assertEquals(20, new Personnummer("201701102384").getYearDigits());
        assertEquals(20, new Personnummer("20080903-2386").getYearDigits());
    }

    @Test
    void getYearDigitFromSNTest() {
        assertEquals(-1, new Samordningsnummer("091079-9824").getYearDigits());
        assertEquals(-1, new Samordningsnummer("0910799824").getYearDigits());
        assertEquals(19, new Samordningsnummer("19091079-9824").getYearDigits());
        assertEquals(19, new Samordningsnummer("190910799824").getYearDigits());
    }

    @Test
    void getYearDigitFromONTest() {
        assertEquals(-1, new Organisationsnummer("556614-3185").getYearDigits());
        assertEquals(16, new Organisationsnummer("16556601-6399").getYearDigits());
        assertEquals(-1, new Organisationsnummer("262000-1111").getYearDigits());
        assertEquals(-1, new Organisationsnummer("857202-7566").getYearDigits());
        assertEquals(16, new Organisationsnummer("16556614-3185").getYearDigits());
        assertEquals(16, new Organisationsnummer("16262000-1111").getYearDigits());
        assertEquals(16, new Organisationsnummer("16857202-7566").getYearDigits());
    }

    @Test
    void getYearDigitFromPNWithWrongShapeTest() {
        assertEquals(-2, new Personnummer("900118+-9811").getYearDigits());
        assertEquals(-2, new Personnummer("46071374 54").getYearDigits());
        assertEquals(-2, new Personnummer("20170110238 4").getYearDigits());
        assertEquals(-2, new Personnummer("200 80903-2386").getYearDigits());
        assertEquals(-2, new Personnummer("").getYearDigits());
    }

    @Test
    void getYearDigitFromSNWithWrongShapeTest() {
        assertEquals(-2, new Samordningsnummer("091079-982").getYearDigits());
        assertEquals(-2, new Samordningsnummer("091799824").getYearDigits());
        assertEquals(-2, new Samordningsnummer("19091 079-9824").getYearDigits());
        assertEquals(-2, new Samordningsnummer("1909107998248").getYearDigits());
        assertEquals(-2, new Samordningsnummer("").getYearDigits());
    }

    @Test
    void getYearDigitFromONWithWrongShapeTest() {        
        assertEquals(-2, new Organisationsnummer("556614 -3185").getYearDigits());
        assertEquals(-2, new Organisationsnummer("16556601-63990").getYearDigits());
        assertEquals(-2, new Organisationsnummer("262000-111").getYearDigits());
        assertEquals(-2, new Organisationsnummer("85720-7566").getYearDigits());
        assertEquals(-2, new Organisationsnummer("16556614-31857").getYearDigits());
        assertEquals(-2, new Organisationsnummer("12000-1111").getYearDigits());
        assertEquals(-2, new Organisationsnummer("1685720 2-7566").getYearDigits());
        assertEquals(-2, new Organisationsnummer("").getYearDigits());
    }
}
