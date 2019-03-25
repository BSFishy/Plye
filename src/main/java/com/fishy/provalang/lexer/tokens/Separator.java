package com.fishy.provalang.lexer.tokens;

import com.fishy.provalang.api.lexerNew.LexTokenInfo;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Separator
{
    public static final CurlyBracketOpen curlyBracketOpen = new CurlyBracketOpen();
    public static final CurlyBracketClose curlyBracketClose = new CurlyBracketClose();

    public static final SquareBracketOpen squareBracketOpen = new SquareBracketOpen();
    public static final SquareBracketClose squareBracketClose = new SquareBracketClose();

    public static final ParenthesisOpen parenthesisOpen = new ParenthesisOpen();
    public static final ParenthesisClose parenthesisClose = new ParenthesisClose();

    public static final Semicolon semicolon = new Semicolon();
    public static final Period period = new Period();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<TokenType> list)
    {
        LexerApi.addTokenTypes(list, new TokenType[]{
                curlyBracketOpen, curlyBracketClose,
                squareBracketOpen, squareBracketClose,
                parenthesisOpen, parenthesisClose,
                semicolon, period
        });
    }

    @Data
    public static class CurlyBracketOpen implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.CurlyBracketOpen);
        }
    }

    @Data
    public static class CurlyBracketClose implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.CurlyBracketClose);
        }
    }

    @Data
    public static class SquareBracketOpen implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.SquareBracketOpen);
        }
    }

    @Data
    public static class SquareBracketClose implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.SquareBracketClose);
        }
    }

    @Data
    public static class ParenthesisOpen implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.ParenthesisOpen);
        }
    }

    @Data
    public static class ParenthesisClose implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.ParenthesisClose);
        }
    }

    @Data
    public static class Semicolon implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.Semicolon);
        }
    }

    @Data
    public static class Period implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.Period);
        }
    }

    public enum SeparatorType
    {
        CurlyBracketOpen,
        CurlyBracketClose,

        SquareBracketOpen,
        SquareBracketClose,

        ParenthesisOpen,
        ParenthesisClose,

        Semicolon,
        Period
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SeparatorData extends TokenType.TokenData
    {

        public final SeparatorType type;

        public SeparatorData(LexTokenInfo info, SeparatorType type)
        {
            super(info);

            this.type = type;
        }
    }
}
