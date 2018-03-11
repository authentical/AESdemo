
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.security.Key;


public class Main {
    private static final String encryptionKey = "Bar12345Bar12345";


    public static void main(String args[]) {

        {
            String in = "923758203945";
            byte[] enc;
            String dec;

            enc = encrypt(in);

            System.out.println(enc);

            dec = decrypt(enc);

            System.out.println(dec);
        }
    }

//////////////////////////////////////////////////////////////////////

    // ENCRYPT
    public static byte[] encrypt(String strClearText) {

        byte[] encryptedText={};

        try {
            Key key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            cipher.init(Cipher.ENCRYPT_MODE, key, ivspec);
            encryptedText = cipher.doFinal(strClearText.getBytes());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return encryptedText;
    }


    // DECRYPT
    public static String decrypt(byte[] strEncrypted) {
        String decryptedText = "";

        try {
            Key key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

            byte[] iv = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            cipher.init(Cipher.DECRYPT_MODE, key, ivspec);
            decryptedText = new String(cipher.doFinal(strEncrypted));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}