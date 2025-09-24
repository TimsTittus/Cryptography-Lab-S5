import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class SimpleDES {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter the message to encrypt: ");
            String originalText = scanner.nextLine();

            System.out.print("Enter a secret key (at least 8 characters): ");
            String keyString = scanner.nextLine();

            if (keyString.length() < 8) {
                System.out.println("Error: The DES key must be at least 8 characters long.");
                return;
            }

            DESKeySpec desKeySpec = new DESKeySpec(keyString.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(originalText.getBytes());
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            
            System.out.println("Original Text: " + originalText);
            System.out.println("Encrypted Text: " + encryptedText);

            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
            String decryptedText = new String(decryptedBytes);

            System.out.println("Decrypted Text: " + decryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}