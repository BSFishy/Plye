package com.fishy.provalang.utils;

public class ArrayUtils
{
    public static <T> boolean contains(T[] haystack, T needle)
    {
        for (T t : haystack)
            if (t.equals(needle))
                return true;

        return false;
    }
}
