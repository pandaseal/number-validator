import java.util.ArrayList;

public abstract class Nummer {
    public String stringForm;
    public int numDigits;
    public Boolean hasCorrectShape;
    public String shapeRegex;
    public ArrayList<ValidityCheck> validityChecks;
}
