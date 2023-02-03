import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberValidator {

    /**
     * Checks if inNumber has the correct shape,
     * that is any of the following shapes:
     * YYYYMMDD-XXXX (13) YYYYMMDDXXXX (12) or YYMMDDDXXXX (10) or 
     * YYMMDD+XXXX (11) or YYMMDD-XXXX (11),
     * where Y,M,D,X are digits and the divider is '+' or '-' if any
     * @param inNumber personal number as String
     * @return true if correct shape, else false
     */
    public static Boolean isCorrectShape(String inNumber) {
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

    public static Boolean isCorrectNumberOfDigits(String inNumber) {
        int digits = 0;
        for (int i = 0; i < inNumber.length(); i++) {
            if (Character.isDigit(inNumber.charAt(i)))
                digits++;
        }
        if (digits == 10 || digits == 12) {
            return true;
        }
        return false;
    }

    public static Boolean isCorrectLength(String inNumber) {
        int nChars = inNumber.length();
        if (nChars >= 10 && nChars <= 13) {
            return true;
        }
        return false;
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
    }
}
