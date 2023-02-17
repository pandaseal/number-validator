package numbervalidator;

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

    /**
     * @param nummer 
     * @return true if date of birth exists, else false
     */
    public Boolean isValidDate(Nummer nummer) {
        if (nummer.hasCorrectShape == null) {
            nummer.hasCorrectShape = ShapeCheck.isCorrectShape(nummer);
        }
        if (!nummer.hasCorrectShape) {
            this.failMessage = "Wrong shape, cannot check date.";
            return false;
        }

        int numDigits = nummer.getNumberOfDigits();
        String DOB = nummer.getDateOfBirthDigits();
        if (nummer.numberType == "samordningsnummer") {
            DOB = getRealDate(DOB);
        }

        boolean hundredPlus = false;
        if (numDigits == 10) {
            String sign = nummer.stringForm.substring(6, 7);
            if (sign == "+") {
                hundredPlus = true;
            } 
        } 
        return dateExists(DOB, hundredPlus);
    }

    /**
     * Method used to get the real date of birth from a samordningsnummer
     * Subtracts 60 from the digits (DD) representing the day in the month,
     * where the date is either YYYYMMDD or YYMMDD.
     * @param date DOB from a samordningsnummer (60 added to DD)
     * @return the real DOB of the samordningsnummer
     */
    public static String getRealDate(String date) {
        int beginIndex;
        int endIndex;
        if (date.length() == 6) {
            beginIndex = 4;
            endIndex = 6;
        } else if (date.length() == 8) {
            endIndex = 8;
            beginIndex = 6;
        } else {
            return "";
        }

        String dateDigits = date.substring(beginIndex, endIndex);
        String realDateDigits = Integer.toString(Integer.parseInt(dateDigits) - 60);
        return date.substring(0, beginIndex) + realDateDigits;
    }

    /**
     * Checks if a date exists.
     * @param date String representation of date to check (6 or 8 digits)
     * @param hundredPlus whether the date was more than 100 years ago or not (only applicable for 6 digit dates)
     * @return true if date exists, else false. Also false if wrong input.
     */
    public static boolean dateExists(String date, boolean hundredPlus) {
        SimpleDateFormat dateFormat;
        if (date.length() == 6) {
            dateFormat = new SimpleDateFormat("yyMMdd");
            if (hundredPlus) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, -200); // 100 year period that ends 100 years ago => starts 200 years ago
                Date hundredYearsAgo = cal.getTime();
                dateFormat.set2DigitYearStart(hundredYearsAgo);
            }
        } else if (date.length() == 8) {
            dateFormat = new SimpleDateFormat("yyyyMMdd");
        } else {
            return false;
        }
        dateFormat.setLenient(false); // false = no room for interpretation
        try {
            dateFormat.parse(date);
        } catch (ParseException pe) { // "Unparseable date"
            return false;
        }
        return true;
    }
}
