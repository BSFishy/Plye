package com.fishy.provalang.parser.pass.association.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class StatementToken extends AssociationToken
{
    public StatementToken(@Nullable AssociationToken parent)
    {
        super(parent);
    }

    public BlockToken promote()
    {
        return new BlockToken(parent, tokens);
    }
}
