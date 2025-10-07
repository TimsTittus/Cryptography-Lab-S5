import java.security.*;
import java.util.Base64;

public class signature {

    private static final String ALGORITHM = "SHA256withRSA";
    private static final int KEY_SIZE = 2048;

    public static void main(String[] args) throws Exception {

        KeyPair keyPair = generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String originalMessage = "This is a secret message to be digitally signed.";
        System.out.println("Original Message: " + originalMessage);

        byte[] digitalSignature = signMessage(originalMessage.getBytes(), privateKey);

        boolean isVerified = verifySignature(originalMessage.getBytes(), digitalSignature, publicKey);
        System.out.println("Signature Verified: " + isVerified);

        String tamperedMessage = "This is a tampered message.";
        System.out.println("\nTampered Message: " + tamperedMessage);

        boolean isTamperedVerified = verifySignature(tamperedMessage.getBytes(), digitalSignature, publicKey);
        System.out.println("Tampered Message Verified: " + isTamperedVerified);
    }

    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] signMessage(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    public static boolean verifySignature(byte[] data, byte[] digitalSignature, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(digitalSignature);
    }
}