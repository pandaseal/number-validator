public class ControlDigitCheck extends ValidityCheck {
    private static int[] multiplier = {2, 1, 2, 1, 2, 1, 2, 1, 2};

    public ControlDigitCheck() {
        this.nameOfCheck = "ControlDigitCheck";
    }

    @Override
    public void performCheck(Nummer nummer) {
        this.pass = isCorrectControlDigit(nummer);
    }

    public Boolean isCorrectControlDigit(Nummer nummer) {
        if (nummer.hasCorrectShape == null) {
            nummer.hasCorrectShape = ShapeCheck.isCorrectShape(nummer);
        }
        if (!nummer.hasCorrectShape) {
            this.failMessage = "Wrong shape, cannot check control digit.";
            return false;
        }

        String shortForm = getShortForm(nummer);
        int[] tenDigitPN = getNumberAsArray(shortForm);
        
        // Luhns Algorithm
        int inControlDigit = tenDigitPN[tenDigitPN.length-1];
        int[] product = new int [multiplier.length];
        for (int i = 0; i < product.length; i++) {
            product[i] = tenDigitPN[i]*multiplier[i];
        }
        int sumOfDigits = sumOfDigits(product);
        int calculatedControlDigit = ((10-(sumOfDigits % 10)) % 10);

        if (inControlDigit == calculatedControlDigit) {
            return true;
        }
        return false;
    }

    public static String getShortForm(Nummer nummer) {
        String inNumber = nummer.stringForm;
        String onlyDigits = inNumber.replaceAll("([[-][+]])", "");

        if (onlyDigits.length() == 12) {
            String shortForm = onlyDigits.substring(2, 12);
            return shortForm;
        } else {
            return onlyDigits;
        } 
    }

    public static int[] getNumberAsArray(String numberAsString) {
        String[] sArr = numberAsString.split(""); 
        int[] iArr = new int [sArr.length];
        for (int i = 0; i < sArr.length; i++) { 
            iArr[i] = Integer.parseInt(sArr[i]); 
        }
        return iArr;
    }

    public static int sumOfDigits(int[] iArr) {
        int sum = 0;
        for (int i = 0; i < iArr.length; i++) {
            int n = iArr[i];
            if (n > 9) {
                int a = Math.floorDiv(n, 10);
                int b = n % 10;
                sum += a + b;
            } else {
                sum += n;
            }
        }
        return sum;
    }
}
