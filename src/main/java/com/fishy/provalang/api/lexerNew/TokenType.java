package com.fishy.provalang.api.lexerNew;

import com.fishy.provalang.api.lexer.LexerTokenInfo;
import lombok.Value;
import lombok.experimental.NonFinal;

public interface TokenType
{
    TokenData cast(char[] buffer);

    @Value
    @NonFinal
    abstract class TokenData
    {
        public final LexerTokenInfo info;
    }
}
