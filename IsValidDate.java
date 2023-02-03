import java.text.ParseException;
import java.text.SimpleDateFormat;

// following example code from: http://www.java2s.com/Code/Java/Data-Type/CheckifaStringisavaliddate.htm
public class IsValidDate {

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false); // false = no room for interpretation
        try {
            dateFormat.parse(inDate);
        } catch (ParseException pe) { // "Unparseable date"
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isValidDate("20040229")); // true
        System.out.println(isValidDate("20050229")); // false
        System.out.println(isValidDate("20170127")); // true
        System.out.println(isValidDate("19030229")); // false
    }
}
