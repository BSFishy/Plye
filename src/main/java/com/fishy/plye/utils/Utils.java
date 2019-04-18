package com.fishy.plye.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;
import java.util.regex.Pattern;

public class Utils
{

    public static <T, K> boolean contains(@NotNull T[] haystack, @NotNull K needle)
    {
        return contains(haystack, needle, Object::equals);
    }

    public static <T, K> boolean contains(@NotNull T[] haystack, @NotNull K needle, BiFunction<T, K, Boolean> activator)
    {
        for (T element : haystack)
        {
            if (activator.apply(element, needle))
                return true;
        }

        return false;
    }

    @Nullable
    public static <T, K> T get(@NotNull T[] haystack, @NotNull K needle)
    {
        return get(haystack, needle, Object::equals);
    }

    @Nullable
    public static <T, K> T get(@NotNull T[] haystack, @NotNull K needle, @NotNull BiFunction<T, K, Boolean> activator)
    {
        for (T element : haystack)
        {
            if (activator.apply(element, needle))
                return element;
        }

        return null;
    }

    @NotNull
    public static String repeat(@NotNull String part, int count)
    {
        return new String(new char[count]).replace("\0", part);
    }

    @NotNull
    public static String generateLeadingRegex(@NotNull String input)
    {
        StringBuilder output = new StringBuilder();
        String[]      split  = input.split("");

        for (String character : split)
        {
            output.append("(?:").append(Pattern.quote(character)).append("|$)");
        }

        return output.toString();
    }

    @NotNull
    @Contract(pure = true)
    public static String generateSingleLeadingRegex(@NotNull String input)
    {
        return "(?:" + input + "|$)";
    }
}
