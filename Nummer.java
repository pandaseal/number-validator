import java.util.ArrayList;

public abstract class Nummer {
    public boolean isValid;
    public String stringForm;
    public String inNumber;
    public String shortForm;
    public int numDigits;
    public boolean hasCorrectShape;
    public String shapeRegex;
    public ArrayList<ValidityCheck> validityChecks;
}
