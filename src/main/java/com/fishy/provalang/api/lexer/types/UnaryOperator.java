package com.fishy.provalang.api.lexer.types;

import com.fishy.provalang.api.lexer.ILexerTokenType;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.LexerCastData;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import com.fishy.provalang.api.lexer.annotation.OverridableLexerTokenType;
import com.fishy.provalang.api.lexer.annotation.OverridableLexerTokenType.Priority;
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

    public static void addDefaultTypes(List<ILexerTokenType> list)
    {
        LexerApi.addTokenTypes(list, new ILexerTokenType[]{increment, decrement,
                negate, not,
                negateBits});
    }

    @OverridableLexerTokenType(priority = Priority.Low)
    @Data
    public static class Increment implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\+[+]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\+\\+"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new UnaryOperatorData(info, OperatorType.Increment);
        }
    }

    @OverridableLexerTokenType(priority = Priority.Low)
    @Data
    public static class Decrement implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("-[-]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("--"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new UnaryOperatorData(info, OperatorType.Decrement);
        }
    }

    @OverridableLexerTokenType(priority = Priority.Low)
    @Data
    public static class Negate implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("-");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("-[^=]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new UnaryOperatorData(info, OperatorType.Negate);
        }
    }

    @OverridableLexerTokenType(priority = Priority.Low)
    @Data
    public static class Not implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("!");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("![^=]"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new UnaryOperatorData(info, OperatorType.Not);
        }
    }

    @OverridableLexerTokenType(priority = Priority.Low)
    @Data
    public static class NegateBits implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("~");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("~[^=]"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
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
    public static class UnaryOperatorData extends ILexerTokenType.LexerTokenData
    {
        public final OperatorType type;

        public UnaryOperatorData(LexerTokenInfo info, OperatorType type)
        {
            super(info);

            this.type = type;
        }
    }
}
