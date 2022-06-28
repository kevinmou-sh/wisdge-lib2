package com.wisdge.utils.security;

public class MD5Test {
    public void test() {
        String message = "simida😈中国";
        String key = "letmein0308";
        try {
            String md5_16 = MD5.encrypt16(message);
            System.out.println("16bit-md5:" + md5_16);
            String md5_32 = MD5.encrypt32(message);
            String md5_32_2 = MD5.encrypt32_2(message);
            String md5_32_3 = MD5.encrypt32_3(message);
            System.out.println("32bit-md5:");
            System.out.println("\t1:  " + md5_32);
            System.out.println("\t2:  " + md5_32_2);
            System.out.println("\t3:  " + md5_32_3);
            String md5_64 = MD5.encrypt64(message);
            System.out.println("64bit-md5:" + md5_64); //
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("md5:" + MD5.encrypt(message));
        System.out.println("md5-hmac:" + MD5.hmac(message, key));
    }
}
