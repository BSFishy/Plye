package com.fishy.provalang.lexer.tokens;

import com.fishy.provalang.api.lexer.LexerTokenInfo;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.TokenType;
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
    public static class New implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeyWordType.New);
        }
    }

    @Data
    public static class Class implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeyWordType.Class);
        }
    }

    @Data
    public static class Static implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeyWordType.Static);
        }
    }

    @Data
    public static class Package implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeyWordType.Package);
        }
    }

    @Data
    public static class Import implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeyWordType.Import);
        }
    }

    @Data
    public static class Public implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeyWordType.Public);
        }
    }

    @Data
    public static class Protected implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeyWordType.Protected);
        }
    }

    @Data
    public static class Private implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new KeywordData(info, KeyWordType.Private);
        }
    }

    public enum KeyWordType
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

        public final KeyWordType keyword;

        public KeywordData(LexerTokenInfo info, KeyWordType keyword)
        {
            super(info);

            this.keyword = keyword;
        }
    }
}
