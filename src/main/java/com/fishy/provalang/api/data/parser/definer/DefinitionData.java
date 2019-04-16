package com.fishy.provalang.api.data.parser.definer;

import com.fishy.provalang.api.data.AbstractMatchingData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Contract;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefinitionData extends AbstractMatchingData
{
    @Contract(pure = true)
    public DefinitionData(boolean value)
    {
        super(value);
    }

    @Contract(pure = true)
    public DefinitionData(boolean value, int lookahead)
    {
        super(value, lookahead);
    }
}
