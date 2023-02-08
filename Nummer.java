import java.util.ArrayList;

public abstract class Nummer {
    public Boolean isValid;
    public String stringForm;
    public String shortForm;
    public int numDigits;
    public Boolean hasCorrectShape;
    public String shapeRegex;
    public ArrayList<ValidityCheck> validityChecks;
}
