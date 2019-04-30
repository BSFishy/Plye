////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.parser.pass.containerization.definer;

import com.fishy.plye.api.parser.definer.Definition;
import com.fishy.plye.parser.pass.containerization.LexerToken;
import com.fishy.plye.parser.pass.containerization.token.ContainerizationToken;
import com.fishy.plye.parser.pass.containerization.token.StatementToken;
import org.jetbrains.annotations.NotNull;

public class ContainerizationDefiner extends AbstractContainerizationDefiner
{
    public static final ContainerizationDefiner                       instance   = new ContainerizationDefiner();
    public static final Definition<ContainerizationToken, LexerToken> definition = instance.getDefinition();

    @Override
    public @NotNull Definition<ContainerizationToken, LexerToken> define()
    {
        return define(() -> new StatementToken(null),
                      mor(
                              m((ContainerizationToken token, ContainerizationToken t) -> t, StatementDefiner.instance),
                              m((ContainerizationToken token, ContainerizationToken t) -> t, GroupDefiner.instance),
                              m((ContainerizationToken token, ContainerizationToken t) -> t, BlockDefiner.instance)
                      ));
    }
}
