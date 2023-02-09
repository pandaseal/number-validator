import java.util.ArrayList;

public class Sammordningsnummer extends Nummer {

    public Sammordningsnummer(String inNumber) {
        this.shapeRegex = "^\\d{6}(?:\\d{2})?[[+][-]]?\\d{4}$";
        this.stringForm = inNumber;
        this.validityChecks = new ArrayList<ValidityCheck>();
        this.validityChecks.add(new ShapeCheck());
        this.validityChecks.add(new ControlDigitCheck());
        this.validityChecks.add(new ValidDateCheck());
        this.validityChecks.add(new PlusUsageCheck());
    }
}
