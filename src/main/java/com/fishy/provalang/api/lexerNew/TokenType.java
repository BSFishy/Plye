package com.fishy.provalang.api.lexerNew;

import lombok.Value;
import lombok.experimental.NonFinal;

public interface TokenType
{
    TokenData cast(LexTokenInfo info, char[] buffer);

    @Value
    @NonFinal
    abstract class TokenData
    {
        public final LexTokenInfo info;
    }
}
