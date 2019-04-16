package com.fishy.provalang.parser.pass.containerization.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class StatementToken extends ContainerizationToken
{
    public StatementToken(@Nullable ContainerizationToken parent)
    {
        super(parent);
    }

    public BlockToken promote()
    {
        return new BlockToken(parent, tokens);
    }
}
