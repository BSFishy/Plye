package com.fishy.provalang.api.lexer.types;

import com.fishy.provalang.api.lexer.ILexerTokenType;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.LexerCastData;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Comment
{
    public static final SingleLine    singleLine    = new SingleLine();
    public static final MultiLine     multiLine     = new MultiLine();
    public static final Documentation documentation = new Documentation();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<ILexerTokenType> list)
    {
        LexerApi.addTokenTypes(list, new ILexerTokenType[]{singleLine, multiLine, documentation});
    }

    @Data
    public static class SingleLine implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.startsWith("//") && !currentString.endsWith("\n");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.startsWith("//") && currentString.endsWith("\n"), true);
        }

        @Override
        public ILexerTokenType.LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new CommentData(info, currentString);
        }
    }

    @Data
    public static class MultiLine implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.startsWith("/*") && !currentString.endsWith("*/");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.startsWith("/*") && currentString.endsWith("*/"), false);
        }

        @Override
        public ILexerTokenType.LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new CommentData(info, currentString.substring(1, currentString.length() - 2));
        }
    }

    @Data
    public static class Documentation implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.startsWith("/**") && !currentString.endsWith("*/");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.startsWith("/**") && currentString.endsWith("*/"), false);
        }

        @Override
        public ILexerTokenType.LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new CommentData(info, currentString.substring(2, currentString.length() - 2));
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class CommentData extends ILexerTokenType.LexerTokenData
    {
        public final String value;

        public CommentData(LexerTokenInfo info, String value)
        {
            super(info);

            this.value = value;
        }
    }
}
