////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.data;

import lombok.Data;
import org.jetbrains.annotations.Contract;

@Data
public class AbstractMatchingData
{
    private final boolean value;
    private final int     lookahead;

    @Contract(pure = true)
    public AbstractMatchingData(boolean value)
    {
        this(value, 0);
    }

    @Contract(pure = true)
    public AbstractMatchingData(boolean value, int lookahead)
    {
        this.value = value;
        this.lookahead = lookahead;
    }
}
