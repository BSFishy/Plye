package com.fishy.provalang.parser.pass.containerization.token;

import com.fishy.provalang.api.parser.pass.PassToken;
import com.fishy.provalang.parser.pass.containerization.LexerToken;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ContainerizationToken extends PassToken
{
    @Nullable
    public final ContainerizationToken parent;
    public final List<LexerToken>      tokens = new ArrayList<>();

    public ContainerizationToken(@Nullable ContainerizationToken parent){
        this.parent = parent;
    }

    @Nullable
    public ContainerizationToken getParent()
    {
        return parent;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasParent()
    {
        return parent != null;
    }

    public ContainerizationToken addToken(LexerToken token)
    {
        tokens.add(token);
        return this;
    }

    public ContainerizationToken addTokens(Collection<LexerToken> tokens) {
        this.tokens.addAll(tokens);
        return this;
    }

    public List<LexerToken> getTokens()
    {
        return tokens;
    }
}
