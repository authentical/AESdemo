
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import java.security.Key;


public class Main {
    private static final String encryptionKey = "Bar12345Bar12345";


    public static void main(String args[]) {

        {
            String in = "Hello????";
            byte[] enc;
            byte[] dec;

            enc = encrypt(in);

            System.out.println(enc);

            dec = decrypt(enc);

            System.out.println(dec);
        }
    }


//
//    // ENCRYPT
//    public static String encrypt(String strClearText, String encryptionKey) {
//        String strData = "";
//
//        try {
//            Key key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.ENCRYPT_MODE, key);
//            byte[] encryptedText = cipher.doFinal(strClearText.getBytes());
//
//            strData = new String(encryptedText);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return strData;
//    }
//
//
//    // DECRYPT
//    public static String decrypt(String strEncrypted, String encryptionKey) {
//        String strData = "";
//
//        try {
//            Key key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
//            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, key);
//
//            strData = new String(cipher.doFinal(strEncrypted.getBytes()));
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return strData;
//    }

    public static byte[] encrypt(String plainTextString) {
        byte[] encrypted = null;
        try {

            Key skeySpec = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[cipher.getBlockSize()];

            IvParameterSpec ivParams = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec,ivParams);
            encrypted  = cipher.doFinal(plainTextString.getBytes());
            System.out.println("encrypted string size:" + encrypted.length);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encrypted;
    }

    public static  byte[]  decrypt(byte[] encryptedBytes) {
        byte[] original = null;
        Cipher cipher = null;
        try {

            Key key = new SecretKeySpec(encryptionKey.getBytes(), "AES");
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //the block size (in bytes), or 0 if the underlying algorithm is not a block cipher
            byte[] ivByte = new byte[cipher.getBlockSize()];
            //This class specifies an initialization vector (IV). Examples which use
            //IVs are ciphers in feedback mode, e.g., DES in CBC mode and RSA ciphers with OAEP encoding operation.
            IvParameterSpec ivParamsSpec = new IvParameterSpec(ivByte);
            cipher.init(Cipher.DECRYPT_MODE, key, ivParamsSpec);
            original= cipher.doFinal(encryptedBytes);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return original;
    }
}