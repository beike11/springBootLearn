package com.stevenw.utils;

import org.springframework.util.CollectionUtils;

import java.util.List;

public class StringUtils {

    public static final String EMPTY_RESULT = "";

    public static <T> String join(List<T> obj, CharSequence split) {
        if (CollectionUtils.isEmpty(obj)) {
            return EMPTY_RESULT;
        }

        StringBuilder result = new StringBuilder();
        for (T t : obj) {
            result.append(t.toString()).append(split);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();

    }

    public static <T> String joinReplace(List<T> obj, CharSequence to, CharSequence split) {
        if (CollectionUtils.isEmpty(obj)) {
            return EMPTY_RESULT;
        }

        StringBuilder result = new StringBuilder();
        for (T t : obj) {
            result.append(to).append(split);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();

    }

    public static String buildCharTemplate(CharSequence aChar, CharSequence split, int count) {
        if (count <= 0) {
            return EMPTY_RESULT;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            result.append(aChar).append(split);
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }
}
