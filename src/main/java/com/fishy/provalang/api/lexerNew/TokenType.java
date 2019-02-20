package com.fishy.provalang.api.lexerNew;

import com.fishy.provalang.api.lexer.LexerTokenInfo;
import lombok.Value;
import lombok.experimental.NonFinal;

public interface TokenType
{
    TokenData cast(LexerTokenInfo info, char[] buffer);

    @Value
    @NonFinal
    abstract class TokenData
    {
        public final LexerTokenInfo info;
    }
}
