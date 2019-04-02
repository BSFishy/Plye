package com.fishy.provalang.api.annotations;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum Priority
{
    Lowest(0, "lowest", "Lowest"),
    Low(1, "low", "Low"),
    Normal(2, "normal", "Normal"),
    High(3, "high", "High"),
    Highest(4, "highest", "Highest");

    private final int    id;
    private final String name;
    private final String value;

    @Contract(pure = true)
    Priority(int id, String name, String value)
    {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    @Contract(pure = true)
    public int id()
    {
        return id;
    }

    @Contract(pure = true)
    public String getName()
    {
        return name;
    }

    @Contract(pure = true)
    public String getValue()
    {
        return value;
    }

    @Nullable
    public static Priority fromName(String name)
    {
        for (Priority p : values())
        {
            if (p.name.equals(name))
                return p;
        }

        return null;
    }

    @Nullable
    public static Priority fromValue(String value)
    {
        for (Priority p : values())
        {
            if (p.value.equals(value))
                return p;
        }

        return null;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString()
    {
        return "Priority[value=" + value + "]";
    }
}