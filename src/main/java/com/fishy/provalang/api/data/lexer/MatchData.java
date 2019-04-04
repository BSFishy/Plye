package com.fishy.provalang.api.data.lexer;

import com.fishy.provalang.api.data.AbstractMatchingData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MatchData extends AbstractMatchingData
{
    public MatchData(boolean value)
    {
        super(value);
    }

    public MatchData(boolean value, int lookahead)
    {
        super(value, lookahead);
    }
}
