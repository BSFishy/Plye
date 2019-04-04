package com.fishy.provalang.parser.pass.association.token;

import com.fishy.provalang.parser.pass.association.LexerToken;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlockToken extends AssociationToken
{
    public final List<AssociationToken> children = new ArrayList<>();
    public boolean open = false;

    public BlockToken(@Nullable AssociationToken parent)
    {
        super(parent);
    }

    public BlockToken(@Nullable AssociationToken parent, List<LexerToken> tokens)
    {
        super(parent);

        this.tokens.addAll(tokens);
    }

    public void addChild(AssociationToken token)
    {
        children.add(token);
    }

    public List<AssociationToken> getChildren()
    {
        return children;
    }
}
