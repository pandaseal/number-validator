import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShapeCheck extends ValidityCheck {

    public ShapeCheck() {
        this.nameOfCheck = "ShapeCheck";
        this.failMessage = "Wrong shape.";
    }

    @Override
    public void performCheck(Nummer nummer) {
        this.pass = isCorrectShape(nummer);
        nummer.hasCorrectShape = this.pass;
    }

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