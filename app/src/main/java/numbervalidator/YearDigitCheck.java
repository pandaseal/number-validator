package numbervalidator;

public class YearDigitCheck extends ValidityCheck {
    public YearDigitCheck() {
        this.nameOfCheck = "YearDigitCheck";
    }

    @Override
    public void performCheck(Nummer nummer) {
        this.pass = yearDigitIs16(nummer);
    }

    public boolean yearDigitIs16(Nummer nummer) {
        if (nummer.hasCorrectShape == null) {
            nummer.hasCorrectShape = ShapeCheck.isCorrectShape(nummer);
        }
        if (!nummer.hasCorrectShape) {
            this.failMessage = "Wrong shape, cannot check.";
            return false;
        }
        int yearDigits = nummer.getYearDigits();
        if (yearDigits == -1) {
            this.passMessage = "No year digit, cannot fail check.";
            return true;
        } else if (yearDigits == 16) {
            return true;
        } else {
            this.failMessage = "Year digit is not 16.";
            return false;
        }
    }
}
