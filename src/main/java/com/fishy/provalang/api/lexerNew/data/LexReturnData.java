package com.fishy.provalang.api.lexerNew.data;

import com.fishy.provalang.api.lexerNew.LexTokenInfo;
import com.fishy.provalang.api.lexerNew.TokenType;
import lombok.Data;

@Data
public class LexReturnData
{
    public final int       length;
    public final TokenType type;

    public TokenType.TokenData cast(LexTokenInfo info, String buffer)
    {
        return type.cast(info, buffer.toCharArray());
    }
}
