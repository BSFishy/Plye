package com.fishy.provalang.lexer.tokens;

import com.fishy.provalang.api.lexer.LexTokenInfo;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Ignored
{
    public static final Return returnToken = new Return();
    public static final CarriageReturn carriageReturn = new CarriageReturn();
    public static final Space space = new Space();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<TokenType> list)
    {
        LexerApi.addTokenTypes(list, new TokenType[]{
                returnToken,
                carriageReturn,
                space
        });
    }

    @Data
    public static class Return implements TokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new IgnoredData(info, IgnoredType.Return);
        }
    }

    @Data
    public static class CarriageReturn implements TokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new IgnoredData(info, IgnoredType.CarriageReturn);
        }
    }

    @Data
    public static class Space implements TokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new IgnoredData(info, IgnoredType.Space);
        }
    }

    public enum IgnoredType
    {
        Return,
        CarriageReturn,
        Space
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class IgnoredData extends TokenType.TokenData
    {
        public final IgnoredType type;

        public IgnoredData(LexTokenInfo info, IgnoredType type)
        {
            super(info);

            this.type = type;
        }
    }
}
