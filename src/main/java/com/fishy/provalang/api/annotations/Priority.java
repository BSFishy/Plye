package com.fishy.provalang.api.annotations;

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

    Priority(int id, String name, String value)
    {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public int id()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getValue()
    {
        return value;
    }

    public static Priority fromName(String name)
    {
        for (Priority p : values())
        {
            if (p.name.equals(name))
                return p;
        }

        return null;
    }

    public static Priority fromValue(String value)
    {
        for (Priority p : values())
        {
            if (p.value.equals(value))
                return p;
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "Priority[value=" + value + "]";
    }
}