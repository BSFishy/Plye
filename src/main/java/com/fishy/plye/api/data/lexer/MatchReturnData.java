package com.fishy.plye.api.data.lexer;

import lombok.Data;

@Data
public class MatchReturnData
{
    private boolean match  = true;
    private int     length = 0;

    public MatchReturnData setMatch(boolean value)
    {
        this.match = value;
        return this;
    }

    public MatchReturnData setMatch(int length)
    {
        this.length = length;
        return this;
    }

    public MatchReturnData setMatch(boolean value, int length)
    {
        this.match = value;
        this.length = length;
        return this;
    }
}
