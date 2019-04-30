////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.lexer.tokens;

import com.fishy.plye.api.lexer.LexTokenInfo;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class UnaryOperator
{
    public static final Increment increment = new Increment();
    public static final Decrement decrement = new Decrement();

    public static final Negate negate = new Negate();
    public static final Not    not    = new Not();

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

    public enum OperatorType
    {
        Increment,
        Decrement,

        Negate,
        Not,

        NegateBits
    }

    public interface OperatorTokenType extends TokenType {}

    public interface PrecedingType extends OperatorTokenType {}

    public interface SucceedingType extends OperatorTokenType {}

    @Data
    public static class Increment implements PrecedingType, SucceedingType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.Increment);
        }
    }

    @Data
    public static class Decrement implements PrecedingType, SucceedingType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.Decrement);
        }
    }

    @Data
    public static class Negate implements PrecedingType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.Negate);
        }
    }

    @Data
    public static class Not implements PrecedingType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.Not);
        }
    }

    @Data
    public static class NegateBits implements PrecedingType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new UnaryOperatorData(info, OperatorType.NegateBits);
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class UnaryOperatorData extends TokenType.TokenData
    {
        public final OperatorType type;

        public UnaryOperatorData(LexTokenInfo info, OperatorType type)
        {
            super(info);

            this.type = type;
        }
    }
}
