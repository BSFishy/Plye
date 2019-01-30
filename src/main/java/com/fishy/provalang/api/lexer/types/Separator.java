package com.fishy.provalang.api.lexer.types;

import com.fishy.provalang.api.lexer.ILexerTokenType;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.LexerCastData;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Separator
{
    public static final CurlyBlockOpen  curlyBlockOpen  = new CurlyBlockOpen();
    public static final CurlyBlockClose curlyBlockClose = new CurlyBlockClose();

    public static final SquareBlockOpen  squareBlockOpen  = new SquareBlockOpen();
    public static final SquareBlockClose squareBlockClose = new SquareBlockClose();

    public static final ParenthesisOpen  parenthesisOpen  = new ParenthesisOpen();
    public static final ParenthesisClose parenthesisClose = new ParenthesisClose();

    public static final Semicolon semicolon = new Semicolon();
    public static final Period    period    = new Period();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<ILexerTokenType> list)
    {
        LexerApi.addTokenTypes(list, new ILexerTokenType[]{curlyBlockOpen, curlyBlockClose,
                squareBlockOpen, squareBlockClose,
                parenthesisOpen, parenthesisClose,
                semicolon, period});
    }

    @Data
    public static class CurlyBlockOpen implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\{");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\{"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new SeparatorData(info, SeparatorType.CurlyBlockOpen);
        }
    }

    @Data
    public static class CurlyBlockClose implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("}");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("}"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new SeparatorData(info, SeparatorType.CurlyBlockClose);
        }
    }

    @Data
    public static class SquareBlockOpen implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\[");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\["), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new SeparatorData(info, SeparatorType.SquareBlockOpen);
        }
    }

    @Data
    public static class SquareBlockClose implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("]");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("]"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new SeparatorData(info, SeparatorType.SquareBlockClose);
        }
    }

    @Data
    public static class ParenthesisOpen implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\(");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\("), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new SeparatorData(info, SeparatorType.ParenthesisOpen);
        }
    }

    @Data
    public static class ParenthesisClose implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\)");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\)"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new SeparatorData(info, SeparatorType.ParenthesisClose);
        }
    }

    @Data
    public static class Semicolon implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(";");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches(";"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new SeparatorData(info, SeparatorType.Semicolon);
        }
    }

    @Data
    public static class Period implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\.");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\."), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new SeparatorData(info, SeparatorType.Period);
        }
    }

    public enum SeparatorType
    {
        CurlyBlockOpen,
        CurlyBlockClose,

        SquareBlockOpen,
        SquareBlockClose,

        ParenthesisOpen,
        ParenthesisClose,

        Semicolon,
        Period
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SeparatorData extends ILexerTokenType.LexerTokenData
    {

        public final SeparatorType type;

        public SeparatorData(LexerTokenInfo info, SeparatorType type)
        {
            super(info);

            this.type = type;
        }
    }
}
