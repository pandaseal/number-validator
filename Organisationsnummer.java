import java.util.ArrayList;

public class Organisationsnummer extends Nummer {
    public Organisationsnummer(String inNumber) {
        //this.shapeRegex = "^\\d{6}(?:\\d{2})?[[+][-]]?\\d{4}$"; TODO
        this.numberType = "organisationsnummer";
        this.stringForm = inNumber;
        this.validityChecks = new ArrayList<ValidityCheck>();
        this.validityChecks.add(new ShapeCheck());
    }
}
