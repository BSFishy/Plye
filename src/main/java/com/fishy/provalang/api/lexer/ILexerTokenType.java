package com.fishy.provalang.api.lexer;

import lombok.Value;
import lombok.experimental.NonFinal;

public interface ILexerTokenType
{
    boolean canCast(String currentString);

    LexerCastData shouldCast(String currentString);

    LexerTokenData cast(LexerTokenInfo info, String currentString);

    @Value
    @NonFinal
    abstract class LexerTokenData
    {
        public final LexerTokenInfo info;
    }
}
