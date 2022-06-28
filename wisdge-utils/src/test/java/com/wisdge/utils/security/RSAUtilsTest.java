package com.wisdge.utils.security;

import java.util.Map;

public class RSAUtilsTest {

    public void test() throws Exception {
        String publicKey = null;
        String privateKey = null;
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            System.err.println("公钥: \n\r" + publicKey);
            System.err.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("公钥加密——私钥解密");
        String content = "这是一行测试RSA数字签名的无意义文字";
        System.out.println("\r加密前文字：\r\n" + content);
        String encrypted = RSAUtils.encryptByPublicKey(content, publicKey);
        System.out.println("加密后文字：\r\n" + encrypted);
        System.out.println("解密后文字: \r\n" + RSAUtils.decryptByPrivateKey(encrypted, privateKey));
    }

    public void test2() throws Exception {
        String publicKey = null;
        String privateKey = null;
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            System.err.println("公钥: \n\r" + publicKey);
            System.err.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\r\n私钥加密——公钥解密");
        String content = "这是一行测试RSA数字签名的无意义文字,这是一行测试RSA数字签名的无意义文字,这是一行测试RSA数字签名的无意义文字,这是一行测试RSA数字签名的无意义文字,这是一行测试RSA数字签名的无意义文字,";
        System.out.println("\r加密前文字：\r\n" + content);
        String encrypted = RSAUtils.encryptByPrivateKey(content, privateKey);
        System.out.println("加密后文字：\r\n" + encrypted);
        String decrypted = RSAUtils.decryptByPublicKey(encrypted, publicKey);
        System.out.println("解密后文字: \r\n" + decrypted);

        System.out.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encrypted, privateKey);
        System.out.println("签名:\r" + sign);
        boolean status = RSAUtils.verify(encrypted, publicKey, sign);
        System.out.println("验证结果:\r" + status);
    }

    public static void keys() {
        String publicKey = null;
        String privateKey = null;
        try {
            Map<String, Object> keyMap = RSAUtils.genKeyPair();
            publicKey = RSAUtils.getPublicKey(keyMap);
            privateKey = RSAUtils.getPrivateKey(keyMap);
            System.out.println("公钥: \n\r" + publicKey);
            System.out.println("私钥： \n\r" + privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
