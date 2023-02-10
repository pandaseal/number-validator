import java.util.ArrayList;

/**
 * Ett organisationsnummer används för juridiska personer (företag, föreningar,
 * myndigheter osv). Första siffran anger vilken juridisk form organisationen har. Det
 * mittersta sifferparet måste vara minst 20. Sista siffran är kontrollsiffra, precis som för
 * personnummer. Ett organisationsnummer kan inleds med “århundrade” 16 om det
 * anges på 12-siffrig form.
 */
public class Organisationsnummer extends Nummer {
    public Organisationsnummer(String inNumber) {
        this.shapeRegex = "^\\d{6}(?:\\d{2})?[-]?\\d{4}$";
        this.numberType = "organisationsnummer";
        this.stringForm = inNumber;
        this.validityChecks = new ArrayList<ValidityCheck>();
        this.validityChecks.add(new ShapeCheck());
        this.validityChecks.add(new ControlDigitCheck());
        this.validityChecks.add(new MonthDigitCheck());
        this.validityChecks.add(new YearDigitCheck());
    }
}
