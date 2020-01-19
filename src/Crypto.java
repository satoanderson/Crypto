import java.util.Scanner;

public class Crypto {

    public static void main(String[] args) {
        /*
        String mes = "Hi there";
        String enc = encryptString(mes, 2, 2);
        System.out.println(enc);
        String ungrup = ungroufy(enc);
        System.out.println(ungrup);
        String dec = decryptString(enc, 2);
        System.out.println(dec);
        */

        Scanner input = new Scanner(System.in);
        System.out.println("Hi there, this is a program to encrypt and decrypt messages. \n");
        System.out.print("Please type your message to be encrypted: ");
        String message = input.nextLine();
        System.out.println();
        System.out.print("Enter the key number to be shifted: ");
        int shift =  input.nextInt();
        System.out.println();
        System.out.print("Do you want your code separated in blocks of how many characters? ");
        int sizeBlocks = input.nextInt();
        System.out.println();

        System.out.println("________________________________________________________");

        System.out.println();

        System.out.println("Encrypting your message...");
        System.out.println();

        String encryptedMessage = encryptString(message,shift,sizeBlocks);
        System.out.print("Your encrypted message is: ");
        System.out.println(encryptedMessage);
        System.out.println();

        System.out.println("________________________________________________________");

        System.out.println();
        System.out.print("Do you want to decrypt your message? (Y)es or (N)o: ");

        String answer = input.next();

        System.out.println();
        if (answer.equalsIgnoreCase("Y") || answer.equalsIgnoreCase("Yes")) {
            System.out.println("Okay, let's do this then!");
            System.out.println();
            String decrypted = decryptString(encryptedMessage, shift, sizeBlocks);
            System.out.print("Your message was: ");
            System.out.println(decrypted);
        } else {
            System.out.println("You message was successfully encrypted.");
        }
        System.out.println();
        System.out.println();
        System.out.println("End of the program");
    }

    //Remove all the spaces, special characters and transform the text to uppercase
    public static String normalizeText(String text) {
        String newText = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '.' || text.charAt(i) == ',' || text.charAt(i) == ':' || text.charAt(i) == ';' || text.charAt(i) == '\"' || text.charAt(i) == '\'' || text.charAt(i) == '!' || text.charAt(i) == '?' || text.charAt(i) == '(' || text.charAt(i) == ')') {
                continue;
            } else if (text.charAt(i) != ' ') {
                newText += String.valueOf(text.charAt(i));
            } else {
                continue;
            }
        }
        return newText.toUpperCase();
    }

    //Split alphabet according to the index number passed in shift variable
    public static String shiftAlphabet(int shift) {
        int start = 0;
        if (shift < 0) {
            start = (int) 'Z' + shift + 1;
        } else {
            start = 'A' + shift;
        }
        String result = "";
        char currChar = (char) start;
        for (; currChar <= 'Z'; ++currChar) {
            result = result + currChar;
        }
        if (result.length() < 26) {
            for (currChar = 'A'; result.length() < 26; ++currChar) {
                result = result + currChar;
            }
        }
        return result;
    }

    //It creates a cesar encryption according to the KEY number that you pass as argument
    public static String cesararify(String text, int key) {
        String alpha = shiftAlphabet(0);
        String decAlpha = shiftAlphabet(key);
        String newText = "";
        for (int i = 0; i < text.length(); i++) {
            newText += decAlpha.charAt(alpha.indexOf(text.charAt(i)));
        }
        return newText;
    }

    //Separete the string in blocs of "div" value, adding "x" to complete the empty parts
    public static String groupify(String text, int div) {
        int numbersOfSubparts = (int) Math.ceil(((double) text.length() / div));
        String newText = "";
        int k = 0;
        for (int i = 0; i < text.length(); i++) {
            if (i % div == 0 && i != 0) {
                newText += " " + text.charAt(i);
                k++;
            } else {
                newText += text.charAt(i);
            }
        }
        int i = 1;
        while ((newText.length() - (numbersOfSubparts - 1) < (numbersOfSubparts * div))) {
            newText += "x";
            i++;
        }

        return newText;
    }

    public static String encryptString(String text, int shift, int sizeCode) {
        text = normalizeText(text);
        text = cesararify(text, shift);
        text = groupify(text, sizeCode);
        return text;
    }

    public static String ungroufy(String text) {
        String newText = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == ' ') {
                newText += "";
            } else {
                newText += text.charAt(i);
            }
        }

            if(text.contains("x")) {
                newText = newText.replace("x", "");
            }

        return newText;
    }

    public static String decryptString(String text, int shift, int sizeCode) {

        text = ungroufy(text);
        String alpha = shiftAlphabet(shift);
        String encAlpha = shiftAlphabet(shift+(26-shift));
        String newText = "";
//        int j = 0;
        for (int i = 0; i < text.length(); i++) {
            newText += encAlpha.charAt(alpha.indexOf(text.charAt(i)));
/*            if (j % sizeCode == 0 && j != 0) {
                newText += " ";
            }
            j++;
*/        }
        return newText;
    }

}