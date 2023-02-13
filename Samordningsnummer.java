/**
 * Ett samordningsnummer används för människor som inte fått ett personnummer
 * men som ändå behöver identifieras i kontakt med myndigheter. De följer samma
 * regler som personnummer, men 60 adderas till datumet (som alltså då kan ta värde
 * mellan 61 och 91 beroende på vilket datum personen är född).
 */
public class Samordningsnummer extends Nummer {
    public Samordningsnummer(String inNumber) {
        super(inNumber);
        this.numberType = "samordningsnummer";
        this.shapeRegex = "^\\d{6}(?:\\d{2})?[[+][-]]?\\d{4}$";
        this.validityChecks.add(new ShapeCheck());
        this.validityChecks.add(new ControlDigitCheck());
        this.validityChecks.add(new ValidDateCheck());
        this.validityChecks.add(new PlusUsageCheck());        
    }
}
