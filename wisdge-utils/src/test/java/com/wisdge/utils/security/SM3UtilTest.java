package com.wisdge.utils.security;

import com.wisdge.utils.security.sm.SM3Util;

public class SM3UtilTest {

    public void testHashAndVerify() {
        String message = "simida😈中国";
        try {
            String hash = SM3Util.hash(message);
            System.out.println("SM3: " + hash);
            System.out.println(SM3Util.verify(message, hash));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testHmacSM3() {
        String message = "simida😈中国";
        String key = "letmein0308";
        try {
            String hmac = SM3Util.hmac(message, key);
            System.out.println("SM3-hmac:" + hmac);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
