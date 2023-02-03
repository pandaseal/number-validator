public class IsCorrectControlDigit {

    public static Boolean isCorrectControlDigit(String inNumber) {

        int[] numberAsArray = getNumberAsArray(inNumber);
        int[] tenDigitPN = new int [10];
        if (numberAsArray.length != 10) {
            if (numberAsArray.length == 12) {
                for (int i = 0; i < tenDigitPN.length; i++) {
                    tenDigitPN[i] = numberAsArray[i+2];
                }
            } else {
                throw new IllegalArgumentException("Wrong input length");
            }
        } else {
            tenDigitPN = numberAsArray;
        }
        
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
        System.out.println(isCorrectControlDigit("201701272394")); // incorrect PN
        System.out.println(isCorrectControlDigit("190302299813")); // incorrect PN
        //System.out.println(isCorrectControlDigit("19030229981a")); // not just digits
        //System.out.println(isCorrectControlDigit("19030229981")); // wrong length => exception

    }


}
