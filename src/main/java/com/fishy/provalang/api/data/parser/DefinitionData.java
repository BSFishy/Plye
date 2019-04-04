package com.fishy.provalang.api.data.parser;

import com.fishy.provalang.api.data.AbstractMatchingData;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefinitionData extends AbstractMatchingData
{
    public DefinitionData(boolean value)
    {
        super(value);
    }

    public DefinitionData(boolean value, int lookahead)
    {
        super(value, lookahead);
    }
}
