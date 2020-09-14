/*Decrypter should contain an instance method called `decrypt` that accepts a String of 4 digits representing a single encrypted integer and performs the following actions:

        Decrypt the string by inverting the encrypt function.
        Return the decrypted integer as a String.
*/
import java.util.Arrays;
import java.util.Scanner;

public class Decrypter {
    public static String decrypt(String value) {
        String data = value;
        String[] sData = null;

        // Iterate through string and make it into firstly a string array then int array
        sData = data.split("");

        int[] deData = new int[sData.length];
        for (int i = 0 ; i < sData.length ; i++) {
            deData[i] = Integer.parseInt(sData[i]);
        }

        // Reverse the encryption with maths
        for (int i = 0 ; i < deData.length ; i++) {
            if (deData[i] < 7) {
                deData[i] = deData[i] + 3;
            } else {
                deData[i] = deData[i] - 7;
            }
        }

        // Swap first digit with third and second digit with fourth
        int temp1 = deData[0];
        int temp2 = deData[1];
        deData[0] = deData[2];
        deData[2] = temp1;
        deData[1] = deData[3];
        deData[3] = temp2;

        // Change string array to string
        String eResult = Arrays.toString(deData).replace(", ", "");

        // Return string of encrypted digits
        return eResult.substring(1, eResult.length() - 1);
    }
}