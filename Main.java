public class Main {
    public static void main(String[] args) {

        Nummer nummer = new Personnummer("900118+9811");

        for (ValidityCheck check : nummer.validityChecks) {
            check.doCheck(nummer);
            // if (!check.pass) {
            //     System.out.println(check.failMessage);
            //     // LOG nummer, check.failMessage
            // } else {
            //     System.out.println("Passed check!");
            // }
        }
    }
}
