public abstract class ValidityCheck {
    public String nameOfCheck;
    public String failMessage;
    public String passMessage;
    abstract public boolean passCheck(Nummer nummer); 
}
