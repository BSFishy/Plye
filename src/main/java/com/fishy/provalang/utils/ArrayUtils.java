package com.fishy.provalang.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class ArrayUtils
{
    public static <T> boolean contains(T[] haystack, T needle)
    {
        for (T t : haystack)
            if (t.equals(needle))
                return true;

        return false;
    }

    public static <T> boolean check(@NotNull Collection<? extends T> haystack, Predicate<T> check)
    {
        for (T t : haystack)
            if (check.test(t))
                return true;
        return false;
    }

    public static <T, K> List<T> multiCheck(@NotNull Collection<? extends T> haystack, @NotNull Collection<? extends K> needle, BiPredicate<T, K> predicate)
    {
        List<T> contains = new ArrayList<>();
        for (T t : haystack)
            for (K k : needle)
                if (predicate.test(t, k))
                    contains.add(t);
        return contains;
    }

    @Nullable
    public static <T, K> T deepContains(Map<T, List<K>> haystack, K needle)
    {
        return deepCheck(haystack, (K item) -> item.equals(needle));
    }

    public static <T, K> T deepCheck(Map<T, List<K>> haystack, Predicate<K> predicate)
    {
        for (T t : haystack.keySet())
            for (K item : haystack.get(t))
                if (predicate.test(item))
                    return t;
        return null;
    }
}
