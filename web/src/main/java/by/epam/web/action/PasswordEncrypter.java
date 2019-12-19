package by.epam.web.action;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncrypter {
    public static String encrypt(String enterPass) {
        byte[] bytes = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(enterPass.getBytes("utf-8"));
            bytes = messageDigest.digest();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInteger = new BigInteger(1, bytes);
        String password = bigInteger.toString(16);
        return password;
    }
}
