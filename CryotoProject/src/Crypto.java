import java.util.Scanner;
/*
Crypto project - you will be writing methods of encrypting and decrypting text
 */
public class Crypto {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println();

        System.out.println(" This program helps you to encrypt your messages. Welcome!!!.");
        System.out.println();
        System.out.print(" Please, enter the message you want to encrypt: ");
        String text = input.nextLine();  // Takes the message to be encrypted.
        System.out.println();
        System.out.print(" Please, enter a number (integer) that it will be the \"key\" to encrypt the message: ");
        int shiftValue = input.nextInt();  // Takes the shiftValue for the Caesarify method.
        System.out.println();
        System.out.print(" Please, enter a number to divide the encrypted message in groups of charts: ");
        int groupSize = input.nextInt();  // Takes the size of the group of letters for the shiftValue for the Groupify method.
        groupSize = Math.abs(groupSize);
        String encryptedText = encryptString(text, shiftValue, groupSize);
        System.out.println();
        System.out.println("Here is the encrypted message:\n");
        System.out.println(encryptedText);
    }
        //first method
        // 1. Removes all the spaces from your text // 2. Remove any punctuation(.,:;'"!?())
        // 3. Turn all lower-case letters into upper-case letters // 4. Return the result
    public static String normalizeText(String s) {
        String normalizedText = "";
        int n = s.length();
        int i = 0;
        for(i=0; i < n; i++){  // Char by char if it's a space or a punctuation it's ignored in the normalizedText string .
            String ltrI = String.valueOf(s.charAt(i));  // "ltrI" is the letter in the i position.
            if((!ltrI.equals(" "))&&(!ltrI.equals("."))&&(!ltrI.equals(","))&&(!ltrI.equals(":"))&&(!ltrI.equals(";"))
                    &&(!ltrI.equals("'"))&&(!ltrI.equals("\""))&&(!ltrI.equals("!"))&&(!ltrI.equals("?"))
                    &&(!ltrI.equals("("))&&(!ltrI.equals(")"))){
                normalizedText = normalizedText + ltrI;
            }
        }
        normalizedText = normalizedText.toUpperCase();
        return normalizedText;
    }
        //second method
        /*this will take in the normalised text and insert a capital O & capital B in front of every vowel, including
          and return the obify(means making something difficult to understand) text*/.
    public static String obify(String s) {
        // Introduce an 'O' and a 'B' in front of every vowel.
        String obifiedText = "";
        int n = s.length();
        char ltrChar;
        int i = 0;
        for(i=0; i < n; i++){  // Letter by letter testing if it is a vowel.
            String ltrI = String.valueOf(s.charAt(i)); // Converts the char in the 'i' position to String.
            if(ltrI.equals("A")||ltrI.equals("E")||ltrI.equals("I")||ltrI.equals("O")||ltrI.equals("U")||ltrI.equals("Y")) {
                obifiedText = obifiedText + "OB" + ltrI;
            }
            else { obifiedText = obifiedText + ltrI;
            }
        }
        return obifiedText;
    }
        //third method
        //this method will take every letter individually & shift in down the alphabet a set number of times.
        // Shift each individual character forward by a certain number or "key" (positive or negative)
        // String you wanna encrypt and int n is the "key".
        // You may assume that de input string is normalized.
        // Use the shiftAlphabet function. So if you call shiftAlphabet(2) you will  get
    public static String caesarify(String text, int key) {
        // back the following string: "CDEFGHIJKLMNOPQRSTUVWXYZAB".
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetKey = shiftAlphabet(key);
        String caesarifiedText ="";
        int n = text.length();
        int i = 0;
        for (i = 0; i < n; i++) {
            char letter = text.charAt(i);
            int index_alpha = alphabet.indexOf(String.valueOf(letter));
            caesarifiedText = caesarifiedText + alphabetKey.charAt(index_alpha);
        }
        return caesarifiedText;
    }
        //forth method
        //this function shift does all the shifting in the code as it returns all the code that has been shifted
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for(; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if(result.length() < 26) {
            for(currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }
        //fifth method
        /*ths method chunk the code into user stated sized chunks. This is done by inserting white spaces after a
        certain number of characters.// The function will return the string broken into groups of "n" letters.
        If is not divisible, the last group is completed with x's.You may assume that de input string is normalized.
        */
    public static String groupify(String s, int n) {
        String groupifiedText = "";
        int m = s.length();
        int numX;
        int i;
        if((m % n) != 0){
            numX = n - (m % n); // Count the x's that you need to add to the string to complete the last group, in case you need x's.
            for(i = 0; i < numX; i++){  // add x's at the given 's' string.
                s = s + "x";}
            m = s.length(); // New length after we add the x's.
        }
        for(i = 0; i < m ; i = i + n){
            groupifiedText = groupifiedText + s.substring(i,i+n) + " ";
        }
        return groupifiedText;
    }
        //sixth method
        //this encrypt method pulls everything together
    public static String encryptString(String text, int shiftValue, int groupSize) {
        String normalizedText = normalizeText(text);  // Call normalizeText on the input string.
        String obifiedText = obify(normalizedText);    // Call obify to obfuscate the normalized text.
        String caesarifiedText = caesarify(obifiedText, shiftValue);  // Call caesarify to encript the obfuscated text.
        String groupifiedText = groupify(caesarifiedText, groupSize);  // Call groupify to break the cyphertext in groups of size letters.
        return groupifiedText;    // Return the result to the main method.
    }
}