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

@OverridableLexerTokenType(priority = Priority.Low)
@Data
public class Identifier implements ILexerTokenType
{
    public static final Identifier instance = new Identifier();

    public static void addDefaultType()
    {
        addDefaultType(LexerApi.getTokens());
    }

    public static void addDefaultType(List<ILexerTokenType> list)
    {
        LexerApi.addTokenType(list, instance);
    }

    @Override
    public boolean canCast(String currentString)
    {
        return currentString.matches("[a-zA-Z0-9_]+[^a-zA-Z0-9_]?");
    }

    @Override
    public LexerCastData shouldCast(String currentString)
    {
        return new LexerCastData(currentString.matches("[a-zA-Z0-9_]+[^a-zA-Z0-9_]"), true);
    }

    @Override
    public LexerTokenData cast(LexerTokenInfo info, String currentString)
    {
        return new IdentifierData(info, currentString);
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class IdentifierData extends LexerTokenData
    {
        public final String value;

        public IdentifierData(LexerTokenInfo info, String value)
        {
            super(info);

            this.value = value;
        }
    }
}
