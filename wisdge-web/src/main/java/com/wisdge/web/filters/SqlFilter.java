package com.wisdge.web.filters;

import com.wisdge.utils.StringUtils;

public class SqlFilter {
    /**
     * SQL注入过滤
     *
     * @param str
     *            待验证的字符串
     */
    public static String sqlInject(String str) throws Exception {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        // 去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        // 转换成小写
        str = str.toLowerCase();

        // 非法字符
        String[] keywords = {
                "master", "truncate", "insert", "select",
                "delete", "update", "declare", "alert",
                "create", "drop" };

        // 判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.equals(keyword)) {
                throw new Exception("包含非法字符: " + keyword);
            }
        }

        return str;
    }
}
