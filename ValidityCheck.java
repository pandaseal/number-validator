public abstract class ValidityCheck {
    public String nameOfCheck;
    public String failMessage = "failed";
    public String passMessage = "passed";
    abstract public boolean passCheck(Nummer nummer); 
    abstract public String getFailMessage(); 
    abstract public String getPassMessage(); 
}
