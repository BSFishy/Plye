package com.fishy.plye.parser.pass.containerization.token;

import com.fishy.plye.api.parser.pass.PassToken;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class ContainerizationToken extends PassToken
{
    @Nullable @ToString.Exclude
    public       ContainerizationToken parent;
    public final List<PassToken>       tokens = new ArrayList<>();

    public ContainerizationToken(@Nullable ContainerizationToken parent)
    {
        this.parent = parent;
    }

    @Nullable
    public ContainerizationToken getParent()
    {
        return parent;
    }

    public void setParent(@Nullable ContainerizationToken parent)
    {
        this.parent = parent;
    }

    public boolean hasParent()
    {
        return parent != null;
    }

    public ContainerizationToken addToken(PassToken token)
    {
        tokens.add(token);
        return this;
    }

    public ContainerizationToken addTokens(Collection<PassToken> tokens)
    {
        this.tokens.addAll(tokens);
        return this;
    }

    public List<PassToken> getTokens()
    {
        return tokens;
    }
}
