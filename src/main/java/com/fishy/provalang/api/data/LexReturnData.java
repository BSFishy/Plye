package com.fishy.provalang.api.data;

import com.fishy.provalang.api.lexer.LexTokenInfo;
import com.fishy.provalang.api.lexer.TokenType;
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
