public class Main {

    public static void main(String[] args) {

        Nummer nummer = new Personnummer("900118+9811");
        System.out.println("Checking personnummer: " + nummer.stringForm);

        // nummer.checkValidity();
        // if (nummer.isValid) {
        //     System.out.println(nummer.passMessage);
        // } else {
        //     System.out.println(nummer.failMessage);
        //     System.out.println(nummer.failedChecks);
        // }
        boolean isValid = true;
        for (ValidityCheck check : nummer.validityChecks) {
            if (!check.passCheck(nummer)) {
                isValid = false;
                System.out.println(check.getFailMessage());
            } else {
                System.out.println(check.getPassMessage());
            }
        }
        System.out.println(nummer.stringForm + " isValid: " + isValid);
    }
}
