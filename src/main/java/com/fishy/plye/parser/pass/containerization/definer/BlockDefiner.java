////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.parser.pass.containerization.definer;

import com.fishy.plye.api.parser.definer.Definition;
import com.fishy.plye.lexer.tokens.Separator;
import com.fishy.plye.parser.pass.containerization.LexerToken;
import com.fishy.plye.parser.pass.containerization.token.BlockToken;
import com.fishy.plye.parser.pass.containerization.token.ContainerizationToken;
import com.fishy.plye.parser.pass.containerization.token.StatementToken;
import org.jetbrains.annotations.NotNull;

public class BlockDefiner extends AbstractContainerizationDefiner
{
    public static final BlockDefiner                                  instance   = new BlockDefiner();
    public static final Definition<ContainerizationToken, LexerToken> definition = instance.getDefinition();

    @Override
    public @NotNull Definition<ContainerizationToken, LexerToken> define()
    {
        return define(() -> new BlockToken(null),
                      mwhile(m(ContainerizationToken::addToken,
                               mand(
                                       mnot(Separator.semicolon),
                                       mnot(Separator.blockOpen),
                                       mnot(Separator.blockClose),
                                       mnot(Separator.groupOpen),
                                       mnot(Separator.groupClose)
                               ))),
                      moptional(m(ContainerizationToken::addToken, GroupDefiner.instance)),
                      m(Separator.blockOpen),
                      mwhile(m((ContainerizationToken token, ContainerizationToken t) -> {
                          BlockToken bt;
                          if (token instanceof BlockToken)
                          {
                              bt = (BlockToken) token;
                          }
                          else if (token instanceof StatementToken)
                          {
                              bt = ((StatementToken) token).promote();
                          }
                          else
                          {
                              bt = new BlockToken(token.getParent(), token.getTokens());
                          }

                          t.setParent(bt);
                          return bt.addChild(t);
                      }, ContainerizationDefiner.instance)),
                      m(Separator.blockClose)
        );
    }
}
