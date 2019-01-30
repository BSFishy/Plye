package com.fishy.provalang.api.lexer;

import com.fishy.provalang.api.lexer.annotation.OverridableLexerTokenType;
import com.fishy.provalang.api.lexer.types.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LexerApi
{
    private static final List<ILexerTokenType> tokenTypes = new ArrayList<>();

    public static void addTokenType(ILexerTokenType token)
    {
        addTokenType(tokenTypes, token);
    }

    public static void addTokenType(List<ILexerTokenType> list, ILexerTokenType token)
    {
        if (!list.contains(token))
            list.add(token);
    }

    public static void addTokenTypes(ILexerTokenType[] tokens)
    {
        addTokenTypes(tokenTypes, tokens);
    }

    public static void addTokenTypes(List<ILexerTokenType> list, ILexerTokenType[] tokens)
    {
        for (ILexerTokenType token : tokens)
            addTokenType(list, token);
    }

    public static List<ILexerTokenType> getTokens()
    {
        return tokenTypes;
    }

    // SHOULD CASTS

    public static LexerData canCast(String currentString)
    {
        return canCast(tokenTypes, currentString);
    }

    public static LexerData canCast(List<ILexerTokenType> tokens, String currentString)
    {
        LexerData data = new LexerData(tokens);

        data.canCastElimination(currentString);
        data.sort();

        return data;
    }

    @Nullable
    public static LexerData.LexerShouldCastData shouldCast(LexerData data, String currentString)
    {
        return data.shouldCast(currentString);
    }

    // DEFAULTS

    public static void addDefaultTypes()
    {
        addDefaultTypes(tokenTypes);
    }

    public static void addDefaultTypes(List<ILexerTokenType> list)
    {
        BinaryOperator.addDefaultTypes(list);
        Comment.addDefaultTypes(list);
        Identifier.addDefaultType(list);
        Ignored.addDefaultTypes(list);
        Keyword.addDefaultTypes(list);
        Literal.addDefaultTypes(list);
        OpAssignOperator.addDefaultTypes(list);
        Separator.addDefaultTypes(list);
        UnaryOperator.addDefaultTypes(list);
    }

    // ANNOTATION

    public static OverridableLexerTokenType getOverridable(ILexerTokenType token)
    {
        Class<? extends ILexerTokenType> c = token.getClass();
        return c.isAnnotationPresent(OverridableLexerTokenType.class) ? c.getAnnotation(OverridableLexerTokenType.class) : null;
    }
}
