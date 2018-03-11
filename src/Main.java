
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.security.Key;


public class Main {
    private static final String encryptionKey = "Bar12345Bar12345";


    public static void main(String args[]) {

        {
            String in = "Hello";
            String enc;
            String dec;

            enc = encrypt(in, encryptionKey);

            dec = decrypt(enc, encryptionKey);

            System.out.println(dec);
        }
    }



    // ENCRYPT
    public static String encrypt(String strClearText, String encryptionKey) {
        String strData = "";

        try {
            Key key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptedText = cipher.doFinal(strClearText.getBytes());

            strData = new String(encryptedText);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }


    // DECRYPT
    public static String decrypt(String strEncrypted, String encryptionKey) {
        String strData = "";

        try {
            Key key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);

            strData = new String(cipher.doFinal(strEncrypted.getBytes()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return strData;
    }


}