////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.parser.pass.containerization.token;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
