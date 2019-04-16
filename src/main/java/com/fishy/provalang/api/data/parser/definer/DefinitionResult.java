package com.fishy.provalang.api.data.parser.definer;

import lombok.Data;

@Data
public class DefinitionResult<T>
{
    public final T token;
    public final DefinitionData result;

    public boolean getValue() {
        return result.isValue();
    }

    public int getLookahead() {
        return result.getLookahead();
    }
}
