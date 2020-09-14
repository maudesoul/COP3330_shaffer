import java.util.Arrays;

public class Encrypter {
    public static String encrypt(String value) {
        String data = value;
        String[] sData = null;

     /*   // Validation checks
        // 1. Is it 4 characters/digits long?
        if (data.length() <= 4) {
            // 2. Are all 4 characters/digits integers?
            for (int i = 0 ; i < data.length() ; i++) {
                if (Character.isDigit(data.charAt(i))) {
                } else {
                    System.out.println("Please enter only integers.");
                    System.exit(0);
                }
            }
        } else {
            System.out.println("Please enter exactly 4 digits you would like to encrypt.");
            System.exit(0);
        }
      */

        // Iterate through string and make it into firstly a string array then int array
        sData = data.split("");

        int[] eData = new int[sData.length];
        for (int i = 0 ; i < sData.length ; i++) {
            eData[i] = Integer.parseInt(sData[i]);
        }

        // Replace each digit with the result of adding 7 to the digit and getting the remainder after dividing the new value by 10.
        for (int i = 0 ; i < eData.length ; i++) {
            eData[i] += 7;
            eData[i] = eData[i] % 10;
        }

        // Swap first digit with third and second digit with fourth
        int temp1 = eData[0];
        int temp2 = eData[1];
        eData[0] = eData[2];
        eData[2] = temp1;
        eData[1] = eData[3];
        eData[3] = temp2;

        // Change string array to string
        String eResult = Arrays.toString(eData).replace(", ", "");

        // Return string of encrypted digits
        return eResult.substring(1, eResult.length() - 1);
    }
}