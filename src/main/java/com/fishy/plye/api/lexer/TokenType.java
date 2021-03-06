////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.lexer;

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
