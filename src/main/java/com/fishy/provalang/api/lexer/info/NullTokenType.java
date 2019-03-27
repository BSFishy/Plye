package com.fishy.provalang.api.lexer.info;

import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.api.lexer.LexTokenInfo;
import com.fishy.provalang.api.lexer.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class NullTokenType implements TokenType
{
    public static final NullTokenType instance = new NullTokenType();

    public static LexToken create(LexTokenInfo info)
    {
        return new LexToken(instance, instance.cast(info, null));
    }

    @Override
    public TokenData cast(LexTokenInfo info, char[] buffer)
    {
        return new NullTokenData(info);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class NullTokenData extends TokenData
    {
        public NullTokenData(LexTokenInfo info)
        {
            super(info);
        }
    }
}
