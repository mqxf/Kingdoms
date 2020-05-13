package com.kingdomsofargus.kingdoms.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Placeholders {

    /**
     * Replace the placeholders in a string.
     * Provide map of possible values
     * {} will auto surround what you enter
     * @param s String to replace
     * @param values values to use
     * @return String, with new parts
     */
    public static String replaceStrPlaceholders(String s, Map<String, String> values) {
        s = StringSubstitutor.replace(s, values, "{", "}");
        return s;
    }


    /**
     * see #replaceStrPlaceholders
     * But for a list
     * @param stringList List to replace
     * @param values Values to use
     * @return List, with new parts
     */
    public static List<String> replaceListPlaceholders(List<String> stringList, Map<String, String> values) {
        return stringList.stream().map(str -> replaceStrPlaceholders(str, values)).collect(Collectors.toList());
    }


}
