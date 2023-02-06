import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValidator {

    /**
     * Checks if inNumber has the correct shape,
     * that is any of the following shapes:
     * YYYYMMDD-XXXX (8+4) YYYYMMDDXXXX (8+4) or YYMMDDDXXXX (6+4) or 
     * YYMMDD+XXXX (6+4) or YYMMDD-XXXX (6+4),
     * where Y,M,D,X are digits and the divider is '+' or '-' if any
     * @param inNumber personal number as String
     * @return true if correct shape, else false
     */
    public static Boolean isCorrectShape(String inNumber) {
        // TODO perhaps YYYYMMDD+XXXX isn't a correct shape.. check it out
        final String regex = "^\\d{6}(?:\\d{2})?[[+][-]]?\\d{4}$";
        final String string = inNumber;
        
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        boolean matchFound = matcher.find();
        if(matchFound) {
            return true;
        }    
        return false;
    }

    public static Boolean isValidDate(String inNumber) {
        if (!isCorrectShape(inNumber)) {
            throw new IllegalArgumentException("Cannot check date, shape is wrong.");
        }

        int numDigits = numberOfDigits(inNumber);
        if (numDigits == 10) { 
            String shortDate = inNumber.substring(0, 6);
            String sign = inNumber.substring(6, 7);

            if (sign == "+") {
                return shortDateExists(shortDate, true);
            } else {
                return shortDateExists(shortDate, false);
            } 
        } else if (numDigits == 12) {
            String longDate = inNumber.substring(0, 8);
            return longDateExists(longDate);
        } else {
            throw new IllegalArgumentException("Cannot check date, incorrect number of digits.");
        }
    }

    public static Boolean longDateExists(String longDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false); // false = no room for interpretation
        try {
            dateFormat.parse(longDate);
        } catch (ParseException pe) { // "Unparseable date"
            return false;
        }
        return true;
    }

    public static Boolean shortDateExists(String shortDate, Boolean hundredPlus) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        dateFormat.setLenient(false); // false = no room for interpretation

        if (hundredPlus) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, -200); // 100 year period that ends 100 years ago => starts 200 years ago
            Date hundredYearsAgo = cal.getTime();
            dateFormat.set2DigitYearStart(hundredYearsAgo);
        }
        try {
            dateFormat.parse(shortDate);
        } catch (ParseException pe) { // "Unparseable date"
            return false;
        }
        return true;
    }

    public static int numberOfDigits(String inNumber) {
        int numDigits = 0;
        for (int i = 0; i < inNumber.length(); i++) {
            if (Character.isDigit(inNumber.charAt(i)))
                numDigits++;
        }
        return numDigits;
    }

    public static Boolean isCorrectLength(String inNumber) {
        int nChars = inNumber.length();
        if (nChars >= 10 && nChars <= 13) {
            return true;
        }
        return false;
    }

    public static String getShortForm(String inNumber) {
        if (!isCorrectShape(inNumber)) {
            throw new IllegalArgumentException("Cannot get short form, shape is wrong.");
        }

        String onlyDigits = inNumber.replaceAll("([[-][+]])", "");
        if (onlyDigits.length() == 12) {
            String shortForm = onlyDigits.substring(2, 12);
            return shortForm;
        } else {
            return onlyDigits;
        } 
    }

    public static Boolean isCorrectControlDigit(String inNumber) {
        if (!isCorrectShape(inNumber)) {
            throw new IllegalArgumentException("Cannot check date, shape is wrong.");
        }

        String shortForm = getShortForm(inNumber);
        int[] tenDigitPN = getNumberAsArray(shortForm);
        
        // Luhns Algorithm
        int inControlDigit = tenDigitPN[tenDigitPN.length-1];
        
        int[] multiplier = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int[] product = new int [multiplier.length];
        for (int i = 0; i < product.length; i++) {
            product[i] = tenDigitPN[i]*multiplier[i];
        }
        int sumOfDigits = sumOfDigits(product);
        int calculatedControlDigit = ((10-(sumOfDigits % 10)) % 10);

        if (inControlDigit == calculatedControlDigit) {
            return true;
        }
        return false;
    }

    public static int sumOfDigits(int[] iArr) {
        int sum = 0;
        for (int i = 0; i < iArr.length; i++) {
            int n = iArr[i];
            if (n > 9) {
                int a = Math.floorDiv(n, 10);
                int b = n % 10;
                sum += a + b;
            } else {
                sum += n;
            }
        }
        return sum;
    }

    public static int[] getNumberAsArray(String numberAsString) {
        String[] sArr = numberAsString.split(""); // https://stackoverflow.com/questions/7347856/how-to-convert-a-string-into-an-arraylist
        int[] iArr = new int [sArr.length];
        for (int i = 0; i < sArr.length; i++) { 
            iArr[i] = Integer.parseInt(sArr[i]); // https://www.tutorialspoint.com/How-to-convert-string-to-array-of-integers-in-java
        }
        return iArr;
    }

    public static void main(String[] args) {

        System.out.println(isCorrectControlDigit("201701102384")); // true
        System.out.println(isCorrectControlDigit("141206-2380")); // true
        System.out.println(isCorrectControlDigit("20080903-2386")); // true
        System.out.println(isCorrectControlDigit("7101169295")); // true
        System.out.println(isCorrectControlDigit("198107249289")); // true

        System.out.println(isCorrectControlDigit("201701272394")); // incorrect PN
        System.out.println(isCorrectControlDigit("190302299813")); // incorrect PN (wrong date)
        System.out.println(isValidDate("190302299813")); // false

        //System.out.println(isCorrectControlDigit("19030229981a")); // not just digits
        //System.out.println(isCorrectControlDigit("19030229981")); // wrong length => exception

        /*
        System.out.println(isValidDate("201701102384")); // true
        System.out.println(isValidDate("141206-2380")); // true
        System.out.println(isValidDate("900118+9811")); // true
        System.out.println(isValidDate("190302299813")); // false
        */
        
        //System.out.println(shortDateExists("141206", false)); // true
        //System.out.println(shortDateExists("900118", true)); // true
        

        /*
        //true
        System.out.println(isCorrectShape("201701102384"));       
        System.out.println(isCorrectShape("141206-2380"));         
        System.out.println(isCorrectShape("20080903-2386"));         
        System.out.println(isCorrectShape("7101169295"));        
        System.out.println(isCorrectShape("900118+9811"));       
        // false
        System.out.println(isCorrectShape("20170110238"));       
        System.out.println(isCorrectShape("141206-238"));       
        System.out.println(isCorrectShape("2008090-2386"));       
        System.out.println(isCorrectShape("710116929"));       
        System.out.println(isCorrectShape("900118+981"));  
        System.out.println(isCorrectShape("6765657900118+9811"));
        */     

    }
}
