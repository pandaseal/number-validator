package numbervalidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlusUsageCheck extends ValidityCheck {

    public PlusUsageCheck() {
        this.nameOfCheck = "PlusUsageCheck";
    }

    @Override
    public void performCheck(Nummer nummer) {
        this.pass = isCorrectUseOfPlus(nummer);
    }

    public Boolean isCorrectUseOfPlus(Nummer nummer) {
        if (nummer.hasCorrectShape == null) {
            nummer.hasCorrectShape = ShapeCheck.isCorrectShape(nummer);
        }
        if (!nummer.hasCorrectShape) {
            this.failMessage = "Wrong shape, cannot check date.";
            return false;
        }

        if (hasPlusAnd8digits(nummer)) {
            return has12DigitsAndIsHundredPlus(nummer);
        }
        this.passMessage = "No plus in number to be used wrongly.";
        return true;
    }

    public static Boolean hasPlusAnd8digits(Nummer nummer) {
        final String regex = "^\\d{8}[+]";
        final String string = nummer.stringForm;
        
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        boolean matchFound = matcher.find();
        return matchFound;
    }

    public Boolean has12DigitsAndIsHundredPlus(Nummer nummer) {
        if (nummer.hasCorrectShape == null) {
            nummer.hasCorrectShape = ShapeCheck.isCorrectShape(nummer);
        }
        if (!nummer.hasCorrectShape) {
            this.failMessage = "Wrong shape, cannot check age.";
            return false;
        }
        if (nummer.getNumberOfDigits() != 12) {
            this.failMessage = "Wrong number of digits, cannot check age.";
            return false;
        }

        String dateOfBirth = nummer.getDateOfBirth();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -100); 
        Date hundredYearsAgo = cal.getTime();

        try {
            Date DOB = dateFormat.parse(dateOfBirth);
            Boolean isHundredYears = hundredYearsAgo.after(DOB);
            return isHundredYears;
        } catch (ParseException pe) { // "Unparseable date"
            this.failMessage = "Cannot check age, date of birth does not exist.";
            return false;
        }
    }
}
