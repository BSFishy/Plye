package com.fishy.provalang.parser.pass.association.token;

import com.fishy.provalang.api.parser.pass.PassToken;
import com.fishy.provalang.parser.pass.association.LexerToken;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class AssociationToken extends PassToken
{
    @Nullable
    public final AssociationToken parent;
    public final List<LexerToken> tokens = new ArrayList<>();

    public AssociationToken(@Nullable AssociationToken parent){
        this.parent = parent;
    }

    @Nullable
    public AssociationToken getParent()
    {
        return parent;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasParent()
    {
        return parent != null;
    }

    public void addToken(LexerToken token)
    {
        tokens.add(token);
    }

    public List<LexerToken> getTokens()
    {
        return tokens;
    }
}
