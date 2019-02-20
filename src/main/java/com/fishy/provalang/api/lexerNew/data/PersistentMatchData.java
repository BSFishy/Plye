package com.fishy.provalang.api.lexerNew.data;

import lombok.Data;
import lombok.NonNull;

@Data
public class PersistentMatchData
{
    @NonNull private int previousMatch;

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
