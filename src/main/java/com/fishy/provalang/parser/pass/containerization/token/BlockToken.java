package com.fishy.provalang.parser.pass.containerization.token;

import com.fishy.provalang.parser.pass.containerization.LexerToken;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlockToken extends ContainerizationToken
{
    public final List<ContainerizationToken> children = new ArrayList<>();
    public boolean                           open     = false;

    public BlockToken(@Nullable ContainerizationToken parent)
    {
        super(parent);
    }

    public BlockToken(@Nullable ContainerizationToken parent, List<LexerToken> tokens)
    {
        super(parent);

        this.tokens.addAll(tokens);
    }

    public BlockToken addChild(ContainerizationToken token)
    {
        children.add(token);
        return this;
    }

    public BlockToken addChildren(Collection<ContainerizationToken> tokens) {
        children.addAll(tokens);
        return this;
    }

    public List<ContainerizationToken> getChildren()
    {
        return children;
    }
}
