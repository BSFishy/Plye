////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.parser.pass.containerization.definer;

import com.fishy.plye.api.parser.definer.Definition;
import com.fishy.plye.lexer.tokens.Separator;
import com.fishy.plye.parser.pass.containerization.LexerToken;
import com.fishy.plye.parser.pass.containerization.token.ContainerizationToken;
import com.fishy.plye.parser.pass.containerization.token.GroupToken;
import org.jetbrains.annotations.NotNull;

public class GroupDefiner extends AbstractContainerizationDefiner
{
    public static final GroupDefiner instance = new GroupDefiner();
    public static final Definition<ContainerizationToken, LexerToken> definition = instance.getDefinition();

    @Override
    public @NotNull Definition<ContainerizationToken, LexerToken> define()
    {
        return define(() -> new GroupToken(null),
                      m(Separator.groupOpen),
                      mwhile(mnot(Separator.groupClose, ContainerizationToken::addToken)),
                      m(Separator.groupClose));
    }
}
