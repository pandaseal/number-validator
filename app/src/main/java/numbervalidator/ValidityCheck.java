package numbervalidator;

public abstract class ValidityCheck {
    public boolean pass;
    public String nameOfCheck;
    public String failMessage = "failed";
    public String passMessage = "passed";
    
    abstract public void performCheck(Nummer nummer); 
    
    public String getFailMessage() {
        return this.nameOfCheck + ": " + this.failMessage;
    }

    public String getPassMessage() {
        return this.nameOfCheck + ": " + this.passMessage;
    }
}
