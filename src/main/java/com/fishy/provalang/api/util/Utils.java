package com.fishy.provalang.api.util;

import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Utils
{

    public static <T, K> boolean contains(T[] haystack, K needle)
    {
        return contains(haystack, needle, Object::equals);
    }

    public static <T, K> boolean contains(T[] haystack, K needle, BiFunction<T, K, Boolean> activator)
    {
        for (T element : haystack)
        {
            if (activator.apply(element, needle))
                return true;
        }

        return false;
    }

    public static <T, K> T get(T[] haystack, K needle)
    {
        return get(haystack, needle, Object::equals);
    }

    public static <T, K> T get(T[] haystack, K needle, BiFunction<T, K, Boolean> activator)
    {
        for (T element : haystack)
        {
            if (activator.apply(element, needle))
                return element;
        }

        return null;
    }

    public static String repeat(String part, int count)
    {
        return new String(new char[count]).replace("\0", part);
    }

    public static String generateLeadingRegex(String input)
    {
        StringBuilder output = new StringBuilder();
        String[]      split  = input.split("");

        for (String character : split)
        {
            output.append("(?:").append(Pattern.quote(character)).append("|$)");
        }

        return output.toString();
    }

    public static String generateSingleLeadingRegex(String input)
    {
        return "(?:" + input + "|$)";
    }
}
