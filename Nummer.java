import java.util.ArrayList;

public abstract class Nummer {
    public Boolean isValid;
    public String stringForm;
    public String numberType;
    public String shapeRegex;
    public Boolean hasCorrectShape;
    public int numDigits;
    public ArrayList<ValidityCheck> validityChecks;
    public ArrayList<ValidityCheck> failingChecks;
    public ArrayList<ValidityCheck> passingChecks;

    public void checkValidity() {
        this.failingChecks = new ArrayList<ValidityCheck>();
        this.passingChecks = new ArrayList<ValidityCheck>();
        
        if (this.validityChecks.isEmpty()) {
            System.out.println("Cannot check validity: no ValidityChecks defined for number type " + numberType);
            this.isValid = false;
        } else {
            this.isValid = true;
            for (ValidityCheck check : this.validityChecks) {
                check.performCheck(this);
                if (!check.pass) {
                    this.isValid = false;
                    this.failingChecks.add(check);
                } else {
                    this.passingChecks.add(check);
                }
            }
        }

    }

    public void printValidity() {
        if (this.isValid == null) {
            System.out.println("Must run checkValidity() before printing validity.");
        } else if (this.isValid) {
            System.out.println(this.stringForm + " is a valid " + this.numberType);
        } else {
            System.out.println(this.stringForm + " is not a valid " + this.numberType);
            System.out.println(this.failingChecks.size() + " out of " + this.validityChecks.size() + " ValidityChecks failed:");
            for (ValidityCheck check : this.failingChecks) {
                System.out.println(check.getFailMessage());
            }
        }
    }
}
