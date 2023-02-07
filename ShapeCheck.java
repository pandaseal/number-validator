import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShapeCheck extends ValidityCheck {

    public ShapeCheck() {
        this.failMessage = "ShapeCheck: Number has wrong shape.";
    }

    @Override
    public void doCheck(Nummer nummer) {
        this.pass = isCorrectShape(nummer.stringForm, nummer.shapeRegex);
        nummer.hasCorrectShape = this.pass;
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
    public static Boolean isCorrectShape(final String string, final String regex) {
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        }    
        return false;
    }
}