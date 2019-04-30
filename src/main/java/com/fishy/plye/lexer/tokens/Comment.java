////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.lexer.tokens;

import com.fishy.plye.api.lexer.LexTokenInfo;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
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

    public static void addDefaultTypes(List<TokenType> tokens)
    {
        LexerApi.addTokenTypes(tokens, new TokenType[]{
                singleLine,
                multiLine,
                documentation
        });
    }

    public interface CommentType extends TokenType {}

    @Data
    public static class SingleLine implements CommentType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new CommentData(info, new String(Arrays.copyOfRange(buffer, 1, buffer.length)));
        }
    }

    @Data
    public static class MultiLine implements CommentType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new CommentData(info, new String(Arrays.copyOfRange(buffer, 1, buffer.length - 2)));
        }
    }

    @Data
    public static class Documentation implements CommentType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new CommentData(info, new String(Arrays.copyOfRange(buffer, 2, buffer.length - 2)));
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class CommentData extends TokenType.TokenData
    {
        public final String value;

        public CommentData(LexTokenInfo info, String value)
        {
            super(info);

            this.value = value;
        }
    }
}
