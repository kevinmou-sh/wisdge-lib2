package com.wisdge.utils;

import java.io.UnsupportedEncodingException;

public class StringUtilsTest {

    public String testEncode(String str) throws UnsupportedEncodingException {
        StringBuffer buf = new StringBuffer();
        buf.append("ISO-8859-1 to UTF-8      -->").append(new String(str.getBytes("ISO-8859-1"), "UTF-8")).append("\n");
        buf.append("ISO-8859-1 to GBK        -->").append(new String(str.getBytes("ISO-8859-1"), "GBK")).append("\n");
        buf.append("ISO-8859-1 to GB2312     -->").append(new String(str.getBytes("ISO-8859-1"), "GB2312")).append("\n");
        buf.append("ISO-8859-1 to BIG5       -->").append(new String(str.getBytes("ISO-8859-1"), "BIG5")).append("\n");
        buf.append("GBK        to UTF-8      -->").append(new String(str.getBytes("GBK"), "UTF-8")).append("\n");
        buf.append("GBK        to ISO-8859-1 -->").append(new String(str.getBytes("GBK"), "ISO-8859-1")).append("\n");
        buf.append("GBK        to GB2312     -->").append(new String(str.getBytes("GBK"), "GB2312")).append("\n");
        buf.append("GBK        to BIG5       -->").append(new String(str.getBytes("GBK"), "BIG5")).append("\n");
        buf.append("UTF-8      to ISO-8859-1 -->").append(new String(str.getBytes("UTF-8"), "ISO-8859-1")).append("\n");
        buf.append("UTF-8      to GBK        -->").append(new String(str.getBytes("UTF-8"), "GBK")).append("\n");
        buf.append("UTF-8      to GB2312     -->").append(new String(str.getBytes("UTF-8"), "GB2312")).append("\n");
        buf.append("UTF-8      to BIG5       -->").append(new String(str.getBytes("UTF-8"), "BIG5")).append("\n");

        return buf.toString();
    }
}
