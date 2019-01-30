package com.fishy.provalang.api.lexer;

import lombok.Data;

@Data
public class LexerCastData
{
    public final boolean shouldCast;
    public final boolean isLookAhead;
}
