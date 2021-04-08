package com.stevenw.io;

import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author stevenw
 * @date 2019/11/29
 */
public class FileMD5 {
    public static String getFileHash() throws NoSuchAlgorithmException {
        String st = "sss1";
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte [] digestBytes = messageDigest.digest(st.getBytes());
        StringBuilder stringBuilder = new StringBuilder();
        for (byte a : digestBytes) {
            int n = a & 0x000000ff;
            System.err.println(n);
            System.err.println(Integer.toHexString(n));
            stringBuilder.append(Integer.toHexString(n));
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        List<String> a = null;
        System.err.println(a.isEmpty());
    }
}
