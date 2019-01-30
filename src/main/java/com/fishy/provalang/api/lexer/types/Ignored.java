package com.fishy.provalang.api.lexer.types;

import com.fishy.provalang.api.lexer.ILexerTokenType;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.LexerCastData;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Ignored
{
    public static final Return         returnToken    = new Return();
    public static final CarriageReturn carriageReturn = new CarriageReturn();
    public static final Space          space          = new Space();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<ILexerTokenType> list)
    {
        LexerApi.addTokenTypes(list, new ILexerTokenType[]{returnToken, carriageReturn, space});
    }

    @Data
    public static class Return implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.equals("\n");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.equals("\n"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new IgnoredData(info, IgnoredType.RETURN);
        }
    }

    @Data
    public static class CarriageReturn implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.equals("\r");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.equals("\r"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new IgnoredData(info, IgnoredType.CARRIAGE_RETURN);
        }
    }

    @Data
    public static class Space implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.equals(" ");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.equals(" "), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new IgnoredData(info, IgnoredType.SPACE);
        }
    }

    public enum IgnoredType
    {
        RETURN,
        CARRIAGE_RETURN,
        SPACE
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class IgnoredData extends ILexerTokenType.LexerTokenData
    {
        public final IgnoredType type;

        public IgnoredData(LexerTokenInfo info, IgnoredType type)
        {
            super(info);

            this.type = type;
        }
    }
}
