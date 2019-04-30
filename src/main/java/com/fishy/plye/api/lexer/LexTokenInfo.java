////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.lexer;

import lombok.Data;

@Data
public class LexTokenInfo implements Cloneable
{
    public int line;
    public int column;
    public int length = 0;

    public void increment(int length)
    {
        this.length += length;
    }

    public void incrementColumn(int length)
    {
        this.column += length;
        this.length = 0;
    }

    public void incrementLine()
    {
        line++;
        column = 0;
        length = 0;
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
