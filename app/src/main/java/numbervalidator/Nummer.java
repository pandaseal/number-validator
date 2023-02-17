package numbervalidator;
import java.util.ArrayList;

public abstract class Nummer {
    public Boolean isValid;
    public String stringForm;
    public String numberType;
    public String shapeRegex;
    public Boolean hasCorrectShape;
    public ArrayList<ValidityCheck> validityChecks;
    public ArrayList<ValidityCheck> failingChecks;
    public ArrayList<ValidityCheck> passingChecks;
    
    private int numDigits;
    private String dateOfBirthDigits;

    public Nummer(String inNumber) {
        this.stringForm = inNumber.strip();
        this.validityChecks = new ArrayList<ValidityCheck>();
    }

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

    public void printValidity(boolean verbose) {
        if (this.isValid == null) {
            System.out.println("Must run checkValidity() before printing validity.");
        } else if (this.isValid) {
            System.out.println(this.stringForm + " is a valid " + this.numberType);
        } else {
            System.out.println(this.stringForm + " is not a valid " + this.numberType);
            if (verbose) {
                System.out.println(this.failingChecks.size() + " out of " + this.validityChecks.size() + " ValidityChecks failed:");
                for (ValidityCheck check : this.failingChecks) {
                    System.out.println(check.getFailMessage());
                }
            }
        }
    }

    public int getNumberOfDigits() {
        if (this.hasCorrectShape == null) {
            this.hasCorrectShape = ShapeCheck.isCorrectShape(this);
        }
        if (!this.hasCorrectShape) {
            return -2;
        }
        if (this.numDigits == 0) {
            this.numDigits = countNumberOfDigits(this.stringForm);
        }
        if (this.numDigits == 0) {
            System.out.println("numDigits was in fact 0");
        }
        return this.numDigits;
    }

    public static int countNumberOfDigits(String inNumber) {
        int numDigits = 0;
        for (int i = 0; i < inNumber.length(); i++) {
            if (Character.isDigit(inNumber.charAt(i)))
                numDigits++;
        }
        return numDigits;
    }

    public int getMonthDigits() {
        if (this.hasCorrectShape == null) {
            this.hasCorrectShape = ShapeCheck.isCorrectShape(this);
        }
        if (!this.hasCorrectShape) {
            return -2;
        }

        int beginIndex = 2;
        if (this.getNumberOfDigits() == 12) {
            beginIndex = 4;   
        }
        int endIndex = beginIndex + 2;
        return Integer.parseInt(this.stringForm.substring(beginIndex, endIndex));
    }

    public int getYearDigits() {
        if (this.hasCorrectShape == null) {
            this.hasCorrectShape = ShapeCheck.isCorrectShape(this);
        }
        if (!this.hasCorrectShape) {
            return -2;
        }

        if (this.getNumberOfDigits() == 12) {
            return Integer.parseInt(this.stringForm.substring(0, 2));
        } else {
            /* no year digits to return */
            return -1;
        }
    }

    public String getDateOfBirthDigits() {
        if (this.dateOfBirthDigits != null) {
            return this.dateOfBirthDigits;
        }
        
        if (this.hasCorrectShape == null) {
            this.hasCorrectShape = ShapeCheck.isCorrectShape(this);
        }
        if (!this.hasCorrectShape) {
            return "";
        }

        if (this.getNumberOfDigits() == 10) {
            return this.stringForm.substring(0, 6);
        } else {
            return this.stringForm.substring(0, 8);
            
        }
    }
}
