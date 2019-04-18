package com.fishy.plye.api.data.parser.definer;

import com.fishy.plye.api.data.AbstractMatchingData;
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
