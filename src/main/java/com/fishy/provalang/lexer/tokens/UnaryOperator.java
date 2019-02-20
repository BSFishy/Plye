package com.fishy.provalang.lexer.tokens;

import com.fishy.provalang.api.lexer.LexerTokenInfo;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class UnaryOperator
{
    public static final Increment increment = new Increment();
    public static final Decrement decrement = new Decrement();

    public static final Negate negate = new Negate();
    public static final Not not = new Not();

    public static final NegateBits negateBits = new NegateBits();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<TokenType> list)
    {
        LexerApi.addTokenTypes(list, new TokenType[]{
                increment, decrement,
                negate, not,
                negateBits
        });
    }

    @Data
    public static class Increment implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.Increment);
        }
    }

    @Data
    public static class Decrement implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.Decrement);
        }
    }

    @Data
    public static class Negate implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.Negate);
        }
    }

    @Data
    public static class Not implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.Not);
        }
    }

    @Data
    public static class NegateBits implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.NegateBits);
        }
    }

    public enum OperatorType
    {
        Increment,
        Decrement,

        Negate,
        Not,

        NegateBits
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UnaryOperatorData extends TokenType.TokenData
    {
        public final OperatorType type;

        public UnaryOperatorData(LexerTokenInfo info, OperatorType type)
        {
            super(info);

            this.type = type;
        }
    }
}
