package com.moa.util;

import lombok.extern.log4j.Log4j;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Log4j
public class Util {
    public static String encodeURIComponent(String s)
    {
        String result = null;
        try {
            result = URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\%21", "!")
                    .replaceAll("\\%27", "'")
                    .replaceAll("\\%28", "(")
                    .replaceAll("\\%29", ")")
                    .replaceAll("\\%7E", "~");
        } catch (UnsupportedEncodingException e) {
           log.warn(e.getMessage());
        }
        return result;
    }
}
