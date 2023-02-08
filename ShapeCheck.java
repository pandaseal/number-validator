import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShapeCheck extends ValidityCheck {

    public ShapeCheck() {
        this.nameOfCheck = "ShapeCheck";
        this.failMessage = "Incorrect shape";
    }

    @Override
    public boolean passCheck(Nummer nummer) {
        boolean result = isCorrectShape(nummer);
        nummer.hasCorrectShape = result;
        return result;
    }

    @Override
    public String getFailMessage() {
        return this.nameOfCheck + ": " + this.failMessage;
    }

    @Override
    public String getPassMessage() {
        return this.nameOfCheck + ": " + this.passMessage;
    }

    /**
     * Checks if inNumber has the correct shape,
     * that is any of the following shapes:
     * YYYYMMDD-XXXX (8+4) YYYYMMDDXXXX (8+4) or YYMMDDDXXXX (6+4) or 
     * YYMMDD+XXXX (6+4) or YYMMDD-XXXX (6+4),
     * where Y,M,D,X are digits and the divider is '+' or '-' if any
     * @param inNumber number as String
     * @return true if correct shape, else false
     */
    public static boolean isCorrectShape(Nummer nummer) {
        final String string = nummer.stringForm;
        final String regex = nummer.shapeRegex;
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        }    
        return false;
    }
}