package com.stevenw.utils;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RsaUtils {
    public static final String CHAR_ENCODING = "UTF-8";
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    public static final String PRIVATE_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAsbsqOjjtNJEWdIylvnh2SVnunu+bUD/tyWzBg7/yOEvwV0y+8li6W4JM+z5gg45t/1tjaZamFGocv19n4oW82wIDAQABAkBZ8YwnNtPkPGCOzva586B48ogt6rhA3nlp7B3qnOLp1bsyQriuzyaCflwx6r74Qkwy28GWoY95/QAjrmnJoMXhAiEA7hOAJOIosrkFH+33lUzgzvdGafmp8dBofg+Fwf/csMkCIQC/HJ6XPeY6eSANO6ixCg3W7/AjFacYfUaX92IemzYWgwIgb2SgaKGI+Vaoly/4z9qQtUXtpFg0s/gXbCRHKWZWFEECIQCwiYRqG1DgNOGJt9F1cN8l7FC8aBUJxzd9y5SEx0WodwIhAJUyFWKV3szMoA4Eh/Wn6hWJqDvnJ573qadNejme/K3B";
    public static final String public_key = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALG7Kjo47TSRFnSMpb54dklZ7p7vm1A/7clswYO/8jhL8FdMvvJYuluCTPs+YIOObf9bY2mWphRqHL9fZ+KFvNsCAwEAAQ==";
    private static int KEYSIZE = 512;
    //生成密钥对
    static Map<String, String> getKeyPair() throws Exception {
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);
        /** 生成密匙对 */
        KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        byte[] publicKeyBytes = publicKey.getEncoded();
        String pub = Base64.getEncoder().encodeToString(publicKeyBytes);
        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        byte[] privateKeyBytes = privateKey.getEncoded();
        String pri = Base64.getEncoder().encodeToString(privateKeyBytes);

        Map<String, String> map = new HashMap<>();
        map.put("publicKey", pub);
        map.put("privateKey", pri);
        return map;
    }

    /**
     * 加密方法 source： 源数据
     */
    public static String encrypt(String source, String publicKey)
            throws Exception {
        Key key = getPublicKey(publicKey);
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes();
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return Base64.getEncoder().encodeToString(b1);
    }

    /**
     * 解密算法 cryptograph:密文
     */
    public static String decrypt(String cryptograph, String privateKey)
            throws Exception {
        Key key = getPrivateKey(privateKey);
        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] b1 = Base64.getDecoder().decode(cryptograph.getBytes());
        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }
    
    /**
     * 得到公钥
     *
     * @param key
     *            密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PublicKey getPublicKey(String key) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                Base64.getDecoder().decode(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }
    
    /**
     * 得到私钥
     *
     * @param key
     *            密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(
                Base64.getDecoder().decode(key.getBytes()));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }
    
    public static void main(String[] args) throws Exception {
       Map keyMap =  RsaUtils.getKeyPair();
       String pwd = "232332232323";
       String cryptograph = RsaUtils.encrypt(pwd,public_key);
       System.err.println(cryptograph);
       String result = RsaUtils.decrypt(cryptograph,PRIVATE_KEY);
       System.out.println(result);
    }
}
