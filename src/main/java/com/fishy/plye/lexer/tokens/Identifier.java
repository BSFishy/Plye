package com.fishy.plye.lexer.tokens;

import com.fishy.plye.api.lexer.LexTokenInfo;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
public class Identifier implements TokenType
{
    public static final Identifier instance = new Identifier();

    public static void addDefaultType()
    {
        addDefaultType(LexerApi.getTokens());
    }

    public static void addDefaultType(List<TokenType> list)
    {
        LexerApi.addTokenType(list, instance);
    }

    @Override
    public TokenData cast(LexTokenInfo info, char[] buffer)
    {
        return new IdentifierData(info, new String(buffer));
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class IdentifierData extends TokenType.TokenData
    {
        public final String value;

        public IdentifierData(LexTokenInfo info, String value)
        {
            super(info);

            this.value = value;
        }
    }
}
