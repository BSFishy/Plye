package com.fishy.provalang.api.lexer.types;

import com.fishy.provalang.api.lexer.ILexerTokenType;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.LexerCastData;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import com.fishy.provalang.api.util.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Keyword
{
    public static final New    newToken    = new New();
    public static final Class  classToken  = new Class();
    public static final Static staticToken = new Static();

    public static final Package packageToken = new Package();
    public static final Import importToken = new Import();

    public static final Public    publicToken    = new Public();
    public static final Private   privateToken   = new Private();
    public static final Protected protectedToken = new Protected();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<ILexerTokenType> list)
    {
        LexerApi.addTokenTypes(list, new ILexerTokenType[]{newToken, classToken, staticToken,
                packageToken, importToken,
                publicToken, privateToken, protectedToken});
    }

    @Data
    public static class New implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex("new") + Utils.generateSingleLeadingRegex("[^a-zA-Z0-9_]?"));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("new[^a-zA-Z0-9_]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new KeywordData(info, KeywordData.KeyWord.NEW);
        }
    }

    @Data
    public static class Class implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex("class") + Utils.generateSingleLeadingRegex("[^a-zA-Z0-9_]?"));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("class[^a-zA-Z0-9_]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new KeywordData(info, KeywordData.KeyWord.CLASS);
        }
    }

    @Data
    public static class Static implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex("static") + Utils.generateSingleLeadingRegex("[^a-zA-Z0-9_]?"));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("static[^a-zA-Z0-9_]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new KeywordData(info, KeywordData.KeyWord.STATIC);
        }
    }

    @Data
    public static class Package implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex("package") + Utils.generateSingleLeadingRegex("[^a-zA-Z0-9_]?"));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("package[^a-zA-Z0-9_]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new KeywordData(info, KeywordData.KeyWord.PACKAGE);
        }
    }

    @Data
    public static class Import implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex("import") + Utils.generateSingleLeadingRegex("[^a-zA-Z0-9_]?"));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("import[^a-zA-Z0-9_]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new KeywordData(info, KeywordData.KeyWord.IMPORT);
        }
    }

    @Data
    public static class Public implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex("public") + Utils.generateSingleLeadingRegex("[^a-zA-Z0-9_]?"));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("public[^a-zA-Z0-9_]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new KeywordData(info, KeywordData.KeyWord.PUBLIC);
        }
    }

    @Data
    public static class Protected implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex("protected") + Utils.generateSingleLeadingRegex("[^a-zA-Z0-9_]?"));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("protected[^a-zA-Z0-9_]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new KeywordData(info, KeywordData.KeyWord.PROTECTED);
        }
    }

    @Data
    public static class Private implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex("private"));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("private[^a-zA-Z0-9_]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new KeywordData(info, KeywordData.KeyWord.PRIVATE);
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class KeywordData extends ILexerTokenType.LexerTokenData
    {

        public final KeyWord keyword;

        public KeywordData(LexerTokenInfo info, KeyWord keyword)
        {
            super(info);

            this.keyword = keyword;
        }

        public enum KeyWord
        {
            NEW,
            CLASS,
            STATIC,

            PACKAGE,
            IMPORT,

            PUBLIC,
            PROTECTED,
            PRIVATE
        }
    }
}
