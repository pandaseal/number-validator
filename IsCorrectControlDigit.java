import java.util.Arrays;

public class IsCorrectControlDigit {

    public static Boolean isCorrectControlDigit(String inNumber) {

        int[] numberAsArray = getNumberAsArray(inNumber);
        /**if (iArr.length != 10) {
            return false;
        }**/

        int[] digitsOfInterest = new int [numberAsArray.length-1];
        for (int i = 0; i < digitsOfInterest.length; i++) {
            digitsOfInterest[i] = numberAsArray[i];
        }
        int inControlDigit = numberAsArray[numberAsArray.length-1];
        int calculatedControlDigit = lughnsAlgorithm(digitsOfInterest);

        if (inControlDigit == calculatedControlDigit) {
            return true;
        }

        return false;
    }  

    public static int lughnsAlgorithm(int[] digitsOfInterest) {

        if (digitsOfInterest.length != 9) {
            throw new IllegalArgumentException("Wrong length for Luhns Algorithm!");
        }

        int[] multiplier = {2, 1, 2, 1, 2, 1, 2, 1, 2};
        int[] product = new int [multiplier.length];
        for (int i = 0; i < product.length; i++) {
            product[i] = digitsOfInterest[i]*multiplier[i];
        }
        int sumOfDigits = sumOfDigits(product);
        int calculatedControlDigit = ((10-(sumOfDigits % 10)) % 10);

        return calculatedControlDigit;
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

        //System.out.println(Arrays.toString(getNumberAsArray("201701102384")));
        System.out.println(isCorrectControlDigit("201701102384")); // true

        //System.out.println(Integer.toString(lughnsAlgorithm(getNumberAsArray("811218987"))));
    }


}
