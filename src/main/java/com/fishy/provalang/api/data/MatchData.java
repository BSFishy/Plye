package com.fishy.provalang.api.data;

import lombok.Data;
import org.jetbrains.annotations.Contract;

@Data
public class MatchData
{
    private final boolean value;
    private final int     lookahead;

    @Contract(pure = true)
    public MatchData(boolean value)
    {
        this.value = value;
        this.lookahead = 0;
    }

    @Contract(pure = true)
    public MatchData(boolean value, int lookahead)
    {
        this.value = value;
        this.lookahead = lookahead;
    }
}
