package com.fishy.provalang.api.lexerNew.data;

import lombok.Data;

@Data
public class MatchReturnData
{
    private boolean match = true;
    private int length = 0;

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
