package com.wisdge.utils.security.sm;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class SM3Util extends GMBaseUtil {

    public static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }

    public static String hash(String src) {
        return ByteUtils.toHexString(hash(src.getBytes(StandardCharsets.UTF_8)));
    }

    public static boolean verify(byte[] srcData, byte[] sm3Hash) {
        byte[] newHash = hash(srcData);
        if (Arrays.equals(newHash, sm3Hash)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean verify(String srcData, String sm3Hash) {
        return verify(srcData.getBytes(StandardCharsets.UTF_8), ByteUtils.fromHexString(sm3Hash));
    }

    /**
     * hmac sm3 encrypt
     * @param srcData byte[] 加密内容
     * @param key byte[] 密钥
     * @return
     */
    public static byte[] hmac(byte[] srcData, byte[] key) {
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] result = new byte[mac.getMacSize()];
        mac.doFinal(result, 0);
        return result;
    }

    /**
     * hmac sm3 encrypt
     * @param message String 加密内容
     * @param key String 密钥
     * @return
     */
    public static String hmac(String message, String key) {
        return ByteUtils.toHexString(hmac(message.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8)));
    }

}
