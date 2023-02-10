import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ValidDateCheck extends ValidityCheck {

    public ValidDateCheck() {
        this.nameOfCheck = "ValidDateCheck";
    }

    @Override
    public void performCheck(Nummer nummer) {
        this.pass = isValidDate(nummer);
    }

    public Boolean isValidDate(Nummer nummer) {
        if (nummer.hasCorrectShape == null) {
            nummer.hasCorrectShape = ShapeCheck.isCorrectShape(nummer);
        }
        if (!nummer.hasCorrectShape) {
            this.failMessage = "Wrong shape, cannot check date.";
            return false;
        }

        int numDigits = nummer.getNumberOfDigits();

        if (numDigits == 10) {
            String shortDate;
            String sign = nummer.stringForm.substring(6, 7);

            if (nummer.numberType == "samordningsnummer") {
                shortDate = getRealDate(nummer.stringForm, true);

            } else {
                shortDate = nummer.stringForm.substring(0, 6);
            }

            if (sign == "+") {
                return shortDateExists(shortDate, true);
            } else {
                return shortDateExists(shortDate, false);
            } 
        } else if (numDigits == 12) {
            String longDate;
            if (nummer.numberType == "samordningsnummer") {
                longDate = getRealDate(nummer.stringForm, false);
            } else {
                longDate = nummer.stringForm.substring(0, 8);
            }
            return longDateExists(longDate);
        } else {
            this.failMessage = "Wrong number of digits, cannot check date.";
            return false;        
        }
    }

    /**
     * Method used to get the real date of birth from a samordningsnummer
     * Subtracts 60 from the digits representing the date (day of month).
     * @param stringForm of a samordningsnummer
     * @param shortForm true if number has 10 digits, false if 12 digits
     * @return the real DOB of a person with a samordningsnummer
     */
    public static String getRealDate(String stringForm, boolean shortForm) {
        int beginIndex = 4;
        int endIndex = 6;
        if (!shortForm) {
            beginIndex = 6;
            endIndex = 8;
        } 
        String dateDigits = stringForm.substring(beginIndex, endIndex);
        String realDateDigits = Integer.toString(Integer.parseInt(dateDigits) - 60);
        return stringForm.substring(0, beginIndex) + realDateDigits;
    }

    public static int numberOfDigits(String inNumber) {
        int numDigits = 0;
        for (int i = 0; i < inNumber.length(); i++) {
            if (Character.isDigit(inNumber.charAt(i)))
                numDigits++;
        }
        return numDigits;
    }

    public static Boolean longDateExists(String longDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false); // false = no room for interpretation
        try {
            dateFormat.parse(longDate);
        } catch (ParseException pe) { // "Unparseable date"
            return false;
        }
        return true;
    }

    public static Boolean shortDateExists(String shortDate, Boolean hundredPlus) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        dateFormat.setLenient(false); // false = no room for interpretation

        if (hundredPlus) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -200); // 100 year period that ends 100 years ago => starts 200 years ago
            Date hundredYearsAgo = cal.getTime();
            dateFormat.set2DigitYearStart(hundredYearsAgo);
        }
        try {
            dateFormat.parse(shortDate);
        } catch (ParseException pe) { // "Unparseable date"
            return false;
        }
        return true;
    }
}
