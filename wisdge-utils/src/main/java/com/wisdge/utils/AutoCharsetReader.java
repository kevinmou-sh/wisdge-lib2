package com.wisdge.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class AutoCharsetReader {
    private final static String[] _defaultCharsets = {
            "US-ASCII",
            "UTF-8",
            "GB2312",
            "BIG5",
            "GBK",
            "GB18030",
            "UTF-16BE",
            "UTF-16LE",
            "UTF-16",
            "UNICODE"};

    public static Charset quickDetect(InputStream inputStream) throws IOException {
        for(String charsetName : _defaultCharsets) {
            Charset charset = Charset.forName(charsetName);
            CharsetDecoder charsetDecoder = charset.newDecoder();
            try (InputStreamReader reader = new InputStreamReader(inputStream, charsetDecoder)) {
                reader.read();
                return charset;
            } catch (Exception e) {
                continue;
            }
        }
        throw new IOException("Detecting charset failed");
    }

    public static Charset quickDetect(InputStream inputStream, int length) throws IOException {
        for(String charsetName : _defaultCharsets) {
            Charset charset = Charset.forName(charsetName);
            CharsetDecoder charsetDecoder = charset.newDecoder();
            try (InputStreamReader reader = new InputStreamReader(inputStream, charsetDecoder)) {
                char[] cbuf = new char[length];
                reader.read(cbuf);
                return charset;
            } catch (Exception e) {
                continue;
            }
        }
        throw new IOException("Detecting charset failed");
    }
}
