package cajero;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Seguridad {

    private static final String ALGORITMO= "AES";
    
    private static final String CLAVE_SECRETA = "miclavesecreta12";

    private static SecretKey getSecretKey() {
        byte[] keyBytes = CLAVE_SECRETA.getBytes();
        return new SecretKeySpec(keyBytes, ALGORITMO);
    }

    public static String cifrar(String data) {
        byte[] encryptedBytes = null;
        try {
            SecretKey secretKey = getSecretKey();
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            encryptedBytes = cipher.doFinal(data.getBytes());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String descifrar(String encryptedData) {
        byte[] decryptedBytes = null;
        try {
            SecretKey secretKey = getSecretKey();
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
            decryptedBytes = cipher.doFinal(decodedBytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new String(decryptedBytes);
    }
}
