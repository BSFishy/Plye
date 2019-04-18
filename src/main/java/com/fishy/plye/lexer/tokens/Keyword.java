package com.fishy.plye.lexer.tokens;

import com.fishy.plye.api.lexer.LexTokenInfo;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Keyword
{
    public static final New    newToken    = new New();
    public static final Class  classToken  = new Class();
    public static final Static staticToken = new Static();

    public static final Package packageToken = new Package();
    public static final Import  importToken  = new Import();

    public static final Public    publicToken    = new Public();
    public static final Protected protectedToken = new Protected();
    public static final Private   privateToken   = new Private();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<TokenType> list)
    {
        LexerApi.addTokenTypes(list, new TokenType[]{
                newToken, classToken, staticToken,
                packageToken, importToken,
                publicToken, protectedToken, privateToken
        });
    }

    @Data
    public static class New implements KeywordTokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeywordType.New);
        }
    }

    @Data
    public static class Class implements KeywordTokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeywordType.Class);
        }
    }

    @Data
    public static class Static implements KeywordTokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeywordType.Static);
        }
    }

    @Data
    public static class Package implements KeywordTokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeywordType.Package);
        }
    }

    @Data
    public static class Import implements KeywordTokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeywordType.Import);
        }
    }

    @Data
    public static class Public implements VisibilityTokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeywordType.Public);
        }
    }

    @Data
    public static class Protected implements VisibilityTokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeywordType.Protected);
        }
    }

    @Data
    public static class Private implements VisibilityTokenType
    {

        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeywordType.Private);
        }
    }

    public enum KeywordType
    {
        New,
        Class,
        Static,

        Package,
        Import,

        Public,
        Protected,
        Private
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class KeywordData extends TokenType.TokenData
    {

        public final KeywordType keyword;

        public KeywordData(LexTokenInfo info, KeywordType keyword)
        {
            super(info);

            this.keyword = keyword;
        }
    }

    public interface KeywordTokenType extends TokenType {}

    public interface VisibilityTokenType extends KeywordTokenType {}
}
