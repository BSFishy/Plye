package com.fishy.provalang.api.lexerNew.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersistentMatchData
{
    private int previousMatch;

    public static PersistentMatchData of(MatchData data)
    {
        return new PersistentMatchData(data.getMatchNumber());
    }

    public void increment()
    {
        previousMatch++;
    }

    public void decrement()
    {
        previousMatch--;
    }
}
