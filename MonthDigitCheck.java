public class MonthDigitCheck extends ValidityCheck {
    public MonthDigitCheck() {
        this.nameOfCheck = "MonthDigitCheck";
    }

    @Override
    public void performCheck(Nummer nummer) {
        this.pass = monthDigitIsAtLeast20(nummer);
    }

    public boolean monthDigitIsAtLeast20(Nummer nummer) {
        if (nummer.hasCorrectShape == null) {
            nummer.hasCorrectShape = ShapeCheck.isCorrectShape(nummer);
        }
        if (!nummer.hasCorrectShape) {
            this.failMessage = "Wrong shape, cannot check.";
            return false;
        }

        if (nummer.getMonthDigits() >= 20) {
            return true;
        }
        this.failMessage = "Month digit is not at least 20.";
        return false;
    }

}
