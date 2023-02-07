import java.util.ArrayList;
import java.util.Date;

public class Personnummer extends Nummer {
    public Date dateOfBirth;
    public boolean isHundredPlus;

    public Personnummer(String inNumber) {
        this.stringForm = inNumber;
        this.validityChecks = new ArrayList<ValidityCheck>();
        this.validityChecks.add(new ShapeCheck());
        this.shapeRegex = "^\\d{6}(?:\\d{2})?[[+][-]]?\\d{4}$";
    }
}