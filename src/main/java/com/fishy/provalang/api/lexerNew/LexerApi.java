package com.fishy.provalang.api.lexerNew;

import java.util.ArrayList;
import java.util.List;

public class LexerApi
{
    // LIST STUFF

    private static final List<TokenType> tokenTypes = new ArrayList<>();

    public static void addTokenType(TokenType token)
    {
        addTokenType(tokenTypes, token);
    }

    public static void addTokenType(List<TokenType> list, TokenType token)
    {
        if (!list.contains(token))
            list.add(token);
    }

    public static void addTokenTypes(TokenType[] tokens)
    {
        addTokenTypes(tokenTypes, tokens);
    }

    public static void addTokenTypes(List<TokenType> list, TokenType[] tokens)
    {
        for (TokenType token : tokens)
            addTokenType(list, token);
    }

    public static List<TokenType> getTokens()
    {
        return tokenTypes;
    }

    // TOKEN STUFF
}
