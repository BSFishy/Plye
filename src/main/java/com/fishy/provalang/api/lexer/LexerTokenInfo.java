package com.fishy.provalang.api.lexer;

import lombok.Data;

@Data
public class LexerTokenInfo implements Cloneable
{
    public int line;
    public int column;
    public int length = 1;

    private int invisibleColumn = 0;

    public void incrementLine()
    {
        line++;
        column = 0;
        invisibleColumn = 0;
        length = 1;
    }

    public void increment()
    {
        length++;
        invisibleColumn++;
    }

    public void incrementColumn()
    {
        column = invisibleColumn;
        length = 1;
    }

    public void decrement()
    {
        length--;
        invisibleColumn--;
    }

    @Override
    public LexerTokenInfo clone()
    {
        try
        {
            return (LexerTokenInfo) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            return null;
        }
    }
}
