package numbervalidator;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ShapeCheckTest {
    @Test
    void isCorrectShapePNTest() {
        assertTrue(ShapeCheck.isCorrectShape(new Personnummer("000000-0000")));
        assertTrue(ShapeCheck.isCorrectShape(new Personnummer("00000000-0000")));
        assertTrue(ShapeCheck.isCorrectShape(new Personnummer("0000000000")));
        assertTrue(ShapeCheck.isCorrectShape(new Personnummer("000000000000")));
        assertTrue(ShapeCheck.isCorrectShape(new Personnummer("000000+0000")));
        assertTrue(ShapeCheck.isCorrectShape(new Personnummer("00000000+0000")));
    }

    @Test
    void isINCorrectShapePNTest() {
        assertFalse(ShapeCheck.isCorrectShape(new Personnummer("000000-00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Personnummer("00000000-00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Personnummer("00000000000")));
        assertFalse(ShapeCheck.isCorrectShape(new Personnummer("0000000000000")));
        assertFalse(ShapeCheck.isCorrectShape(new Personnummer("000000+00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Personnummer("00000000+00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Personnummer("")));
    }

    @Test
    void isCorrectShapeSNTest() {
        assertTrue(ShapeCheck.isCorrectShape(new Samordningsnummer("000000-0000")));
        assertTrue(ShapeCheck.isCorrectShape(new Samordningsnummer("00000000-0000")));
        assertTrue(ShapeCheck.isCorrectShape(new Samordningsnummer("0000000000")));
        assertTrue(ShapeCheck.isCorrectShape(new Samordningsnummer("000000000000")));
        assertTrue(ShapeCheck.isCorrectShape(new Samordningsnummer("000000+0000")));
        assertTrue(ShapeCheck.isCorrectShape(new Samordningsnummer("00000000+0000")));
    }

    @Test
    void isINCorrectShapeSNTest() {
        assertFalse(ShapeCheck.isCorrectShape(new Samordningsnummer("000000-00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Samordningsnummer("00000000-00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Samordningsnummer("00000000000")));
        assertFalse(ShapeCheck.isCorrectShape(new Samordningsnummer("0000000000000")));
        assertFalse(ShapeCheck.isCorrectShape(new Samordningsnummer("000000+00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Samordningsnummer("00000000+00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Samordningsnummer("")));
    }

    @Test
    void isCorrectShapeONTest() {
        assertTrue(ShapeCheck.isCorrectShape(new Organisationsnummer("000000-0000")));
        assertTrue(ShapeCheck.isCorrectShape(new Organisationsnummer("00000000-0000")));
        assertTrue(ShapeCheck.isCorrectShape(new Organisationsnummer("0000000000")));
        assertTrue(ShapeCheck.isCorrectShape(new Organisationsnummer("000000000000")));
    }

    @Test
    void isINCorrectShapeONTest() {
        assertFalse(ShapeCheck.isCorrectShape(new Organisationsnummer("000000-00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Organisationsnummer("00000000-00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Organisationsnummer("00000000000")));
        assertFalse(ShapeCheck.isCorrectShape(new Organisationsnummer("0000000000000")));
        assertFalse(ShapeCheck.isCorrectShape(new Organisationsnummer("000000+00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Organisationsnummer("00000000+00000")));
        assertFalse(ShapeCheck.isCorrectShape(new Organisationsnummer("")));
    }
}
