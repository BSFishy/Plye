package com.fishy.provalang.api.lexer;

import lombok.Data;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LexerData
{
    private final List<ILexerTokenType> tokens = new ArrayList<>();

    public LexerData() {}

    public LexerData(Collection<? extends ILexerTokenType> tokens)
    {
        this.tokens.addAll(tokens);
    }

    // BASIC LIST METHODS

    public <T extends ILexerTokenType> void addCastable(T token)
    {
        tokens.add(token);
    }

    public <T extends ILexerTokenType> void addCastableRange(Collection<T> tokens)
    {
        this.tokens.addAll(tokens);
    }

    public <T extends ILexerTokenType> void addCastable(int index, T token)
    {
        tokens.add(index, token);
    }

    public <T extends ILexerTokenType> void addCastableRange(int index, Collection<T> tokens)
    {
        this.tokens.addAll(index, tokens);
    }

    public List<ILexerTokenType> getTokens()
    {
        return tokens;
    }

    public void sort()
    {
        tokens.sort(new LexerTokenComparer());
    }

    public boolean empty()
    {
        return tokens.isEmpty();
    }

    // ELIMINATION METHODS

    public void canCastElimination(String currentString)
    {
        tokens.removeIf(token -> !token.canCast(currentString));
    }

    @Nullable
    public LexerShouldCastData shouldCast(String currentString)
    {
        for (ILexerTokenType token : tokens)
        {
            LexerCastData data = token.shouldCast(currentString);

            if(data.isShouldCast())
                return new LexerShouldCastData(token, data);
        }

        return null;
    }

    @Data
    public static class LexerShouldCastData
    {
        public final ILexerTokenType token;
        public final LexerCastData   data;
    }
}
