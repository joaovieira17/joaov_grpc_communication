package com.category.utils;

import java.text.Normalizer;

public class StringUtils {

    public static boolean equalsIgnoreCaseAndDiacritic(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return str1 == str2;
        }

        String normalizedStr1 = Normalizer.normalize(str1, Normalizer.Form.NFD)
                .replaceAll("\\p{Mn}", "");
        String normalizedStr2 = Normalizer.normalize(str2, Normalizer.Form.NFD)
                .replaceAll("\\p{Mn}", "");

        return normalizedStr1.equalsIgnoreCase(normalizedStr2);
    }
}
