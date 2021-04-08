package com.stevenw.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class AESUtils {
    
    final static String aesKey = "BXjdcxEGkkG9Qiec";
    final static String AES_ALGORITHM = "AES/ECB/PKCS5Padding";

    /**
     * 暗号化
     * @param aesKey
     * @param data
     * @return
     */
    public static String encode64(String aesKey, String data){
        Key key = new SecretKeySpec(aesKey.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte [] result = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 復号
     * @param aesKey
     * @param data
     * @return
     */
    public static String decode64(String aesKey, String data){
        Key key = new SecretKeySpec(aesKey.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte [] result = cipher.doFinal(Base64.getDecoder().decode(data.getBytes()));
            return new String(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
