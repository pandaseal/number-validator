public class MonthDigitCheck extends ValidityCheck {
    public MonthDigitCheck() {
        this.nameOfCheck = "MonthDigitCheck";
    }

    @Override
    public void performCheck(Nummer nummer) {
        this.pass = false;
    }

}
