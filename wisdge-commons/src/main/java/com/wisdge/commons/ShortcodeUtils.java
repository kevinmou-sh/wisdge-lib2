package com.wisdge.commons;

import com.wisdge.commons.redis.RedisTemplate;
import com.wisdge.utils.StringUtils;
import com.wisdge.utils.security.MD5;
import com.wisdge.utils.security.SHA;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ShortcodeUtils {
    private static final String SHORTCODE_NS = "shortcode";
    private static final String SHORTCODE_SALT = "wisdge";

    @Autowired
    private RedisTemplate redisTemplate;

    protected String getShortCodeWithRedis(String content, Integer ttl, TimeUnit timeUnit) {
        List<String> shorts = getShortUrl(content);
        for (String sc: shorts) {
            String x = (String) redisTemplate.get(SHORTCODE_NS + ":" + sc);
            if (x == null || x.equals(content)) {
                if (ttl == null || ttl.longValue() == 0)
                    redisTemplate.setGlobal(SHORTCODE_NS + ":" + sc, content);
                else
                    redisTemplate.setGlobal(SHORTCODE_NS + ":" + sc, content, ttl, timeUnit);
                return sc;
            }
        }
        return null;
    }

    private static final String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    private List<String> getShortUrl(String content) {
        String hex = MD5.hmac(SHA.encrypt(StringUtils.reverse(content)), SHORTCODE_SALT);
        List<String> shorts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring(i * 8, i * 8 + 8);
            // 这里需要使用 long 型来转换，因为 Integer.parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            StringBuilder outChars = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars.append(chars[(int) index]);
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            shorts.add(outChars.toString());
        }

        shorts.add(getShortCode());
        return shorts;
    }

    private String getShortCode() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str = uuid.substring(i * 4, i * 4 + 4);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 0x3E]);
        }
        return shortBuffer.toString();
    }
}
