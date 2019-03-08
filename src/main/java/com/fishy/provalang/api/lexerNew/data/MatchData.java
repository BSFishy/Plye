package com.fishy.provalang.api.lexerNew.data;

import lombok.Data;

@Data
public class MatchData
{
    private final boolean value;
    private final int     lookahead;

    public MatchData(boolean value)
    {
        this.value = value;
        this.lookahead = 0;
    }

    public MatchData(boolean value, int lookahead)
    {
        this.value = value;
        this.lookahead = lookahead;
    }
}
