package com.fishy.provalang.api.lexerNew;

import lombok.Data;

@Data
public class LexTokenInfo implements Cloneable
{
    public int line;
    public int column;
    public int length = 1;

    public void increment(int length)
    {
        column += length;
        this.length += length;
    }

    public void incrementLine()
    {
        line++;
        column = 0;
        length = 1;
    }

    public void increment()
    {
        length++;
    }

    public void incrementColumn()
    {
        column++;
    }

    @Override
    public LexTokenInfo clone()
    {
        try
        {
            return (LexTokenInfo) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return null;
        }
    }
}
