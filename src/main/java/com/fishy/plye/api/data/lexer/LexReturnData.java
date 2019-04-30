////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.data.lexer;

import com.fishy.plye.api.lexer.LexTokenInfo;
import com.fishy.plye.api.lexer.TokenType;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public class LexReturnData
{
    public final int       length;
    public final TokenType type;

    public TokenType.TokenData cast(@NotNull LexTokenInfo info, @NotNull String buffer)
    {
        return type.cast(info, buffer.toCharArray());
    }
}
