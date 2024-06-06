/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testing;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public class JCAExample {
    public static void main(String[] args) {
        try {
            String message = "Welcome to Educative Answers!";
            String key = "SecretKey12345678"; // Update the key to have a valid length (16 bytes)

            // Truncate or pad the key to 16 bytes
            byte[] keyBytes = Arrays.copyOf(key.getBytes(), 16);

            // Generate a secret key from the key bytes
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

            // Create a Cipher object for AES encryption
            Cipher cipher = Cipher.getInstance("AES");

            // Initialize the cipher in encryption mode with the secret key
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encrypt the message
            byte[] encryptedBytes = cipher.doFinal(message.getBytes());

            // Print the encrypted message
            System.out.println("Encrypted message: " + bytesToHex(encryptedBytes));

            // Initialize the cipher in decryption mode with the secret key
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Decrypt the message
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Print the decrypted message
            System.out.println("Decrypted message: " + new String(decryptedBytes));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    // Array of characters representing hexadecimal digits
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    // Convert a byte array to a hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            // Get the high nibble and convert it to a hexadecimal character
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            // Get the low nibble and convert it to a hexadecimal character
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}