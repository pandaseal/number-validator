public class Personnummer extends Nummer {
public Personnummer(String inNumber) {
        super(inNumber);
        this.shapeRegex = "^\\d{6}(?:\\d{2})?[[+][-]]?\\d{4}$";
        this.numberType = "personnummer";
        this.validityChecks.add(new ShapeCheck());
        this.validityChecks.add(new ControlDigitCheck());
        this.validityChecks.add(new ValidDateCheck());
        this.validityChecks.add(new PlusUsageCheck());
    }
}