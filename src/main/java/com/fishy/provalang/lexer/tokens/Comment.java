package com.fishy.provalang.lexer.tokens;

import com.fishy.provalang.api.lexer.LexTokenInfo;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.TokenType;
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

    @Data
    public static class SingleLine implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new CommentData(info, new String(Arrays.copyOfRange(buffer, 1, buffer.length)));
        }
    }

    @Data
    public static class MultiLine implements TokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new CommentData(info, new String(Arrays.copyOfRange(buffer, 1, buffer.length - 2)));
        }
    }

    @Data
    public static class Documentation implements TokenType
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

    public interface CommentType extends TokenType {}
}
