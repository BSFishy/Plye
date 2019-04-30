////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.parser.pass.containerization.definer;

import com.fishy.plye.api.parser.definer.Definition;
import com.fishy.plye.lexer.tokens.Separator;
import com.fishy.plye.parser.pass.containerization.LexerToken;
import com.fishy.plye.parser.pass.containerization.token.ContainerizationToken;
import com.fishy.plye.parser.pass.containerization.token.StatementToken;
import org.jetbrains.annotations.NotNull;

public class StatementDefiner extends AbstractContainerizationDefiner
{
    public static final StatementDefiner                              instance   = new StatementDefiner();
    public static final Definition<ContainerizationToken, LexerToken> definition = instance.getDefinition();

    @Override
    public @NotNull Definition<ContainerizationToken, LexerToken> define()
    {
        return define(() -> new StatementToken(null),
                      mwhile(mor(m(ContainerizationToken::addToken, mand(
                                       mnot(Separator.semicolon),
                                       mnot(Separator.blockOpen),
                                       mnot(Separator.blockClose),
                                       mnot(Separator.groupOpen),
                                       mnot(Separator.groupClose)
                               )),
                              m(ContainerizationToken::addToken, GroupDefiner.instance))),
                      m(Separator.semicolon)
        );
    }
}
