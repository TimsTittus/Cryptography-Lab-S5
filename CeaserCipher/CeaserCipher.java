package CeaserCipher;
import java.util.Scanner;

public class CeaserCipher {

    public static String encrypt(String text) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                ch = (char) (((ch - 'A' + 3) % 26) + 'A');
            } else if (Character.isLowerCase(ch)) {
                ch = (char) (((ch - 'a' + 3) % 26) + 'a');
            }
            result += ch;
        }
        return result;
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - (shift % 26));
    }

    public static String encrypt(String text, int shift) {
        String result = "";
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                ch = (char) (((ch - 'A' + shift) % 26) + 'A');
            } else if (Character.isLowerCase(ch)) {
                ch = (char) (((ch - 'a' + shift) % 26) + 'a');
            }
            result += ch;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the text:");
        String text = sc.nextLine();

        String encrypted = encrypt(text);
        String decrypted = decrypt(encrypted, 3);

        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}