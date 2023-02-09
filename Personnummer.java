import java.util.ArrayList;
import java.util.Date;

public class Personnummer extends Nummer {
    public Date dateOfBirth;
    public boolean isHundredPlus;

    public Personnummer(String inNumber) {
        this.shapeRegex = "^\\d{6}(?:\\d{2})?[[+][-]]?\\d{4}$";
        this.numberType = "personnummer";
        this.validityChecks = new ArrayList<ValidityCheck>();
        this.validityChecks.add(new ShapeCheck());
        this.validityChecks.add(new ControlDigitCheck());
        this.validityChecks.add(new ValidDateCheck());
        this.validityChecks.add(new PlusUsageCheck());
        
        this.stringForm = inNumber;
    }
}