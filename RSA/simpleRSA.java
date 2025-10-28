import java.util.*;
import java.security.*;
import javax.crypto.*;
import java.nio.charset.StandardCharsets;

public class simpleRSA {
    public static void main(String[] args) throws Exception {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Enter string to encrypt: ");
            String str = sc.nextLine();

            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048);
            KeyPair kp = kpg.generateKeyPair();

            Cipher c = Cipher.getInstance("RSA");
            
            c.init(Cipher.ENCRYPT_MODE, kp.getPublic());
            byte[] enc = c.doFinal(str.getBytes(StandardCharsets.UTF_8));
            String encStr = Base64.getEncoder().encodeToString(enc);

            c.init(Cipher.DECRYPT_MODE, kp.getPrivate());
            byte[] dec = c.doFinal(Base64.getDecoder().decode(encStr));

            System.out.println("\nEncrypted string: " + encStr);
            System.out.println("Decrypted string: " + new String(dec, StandardCharsets.UTF_8));
        }
    }
}