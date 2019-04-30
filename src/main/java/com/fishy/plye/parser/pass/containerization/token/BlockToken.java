package com.fishy.plye.parser.pass.containerization.token;

import com.fishy.plye.api.parser.pass.PassToken;
import com.fishy.plye.parser.pass.containerization.LexerToken;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BlockToken extends ContainerizationToken
{
    public final List<ContainerizationToken> children = new ArrayList<>();

    public BlockToken(@Nullable ContainerizationToken parent)
    {
        super(parent);
    }

    public BlockToken(@Nullable ContainerizationToken parent, List<PassToken> tokens)
    {
        super(parent);

        this.tokens.addAll(tokens);
    }

    public BlockToken addChild(ContainerizationToken token)
    {
        children.add(token);
        return this;
    }

    public BlockToken addChildren(Collection<ContainerizationToken> tokens)
    {
        children.addAll(tokens);
        return this;
    }

    public List<ContainerizationToken> getChildren()
    {
        return children;
    }
}
