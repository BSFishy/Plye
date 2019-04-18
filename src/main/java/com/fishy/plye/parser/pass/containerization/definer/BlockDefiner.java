package com.fishy.plye.parser.pass.containerization.definer;

import com.fishy.plye.api.parser.definer.Definition;
import com.fishy.plye.lexer.tokens.Separator;
import com.fishy.plye.parser.pass.containerization.LexerToken;
import com.fishy.plye.parser.pass.containerization.token.BlockToken;
import com.fishy.plye.parser.pass.containerization.token.ContainerizationToken;
import com.fishy.plye.parser.pass.containerization.token.StatementToken;
import org.jetbrains.annotations.NotNull;

public class BlockDefiner extends ContainerizationDefiner
{
    public static final BlockDefiner                                  instance   = new BlockDefiner();
    public static final Definition<ContainerizationToken, LexerToken> definition = instance.define();

    @Override
    public @NotNull Definition<ContainerizationToken, LexerToken> define()
    {
        return define(() -> new BlockToken(null),
                      mwhile(m(ContainerizationToken::addToken,
                               mnot(
                                       m(Separator.semicolon),
                                       m(Separator.blockOpen),
                                       m(Separator.blockClose)
//                                       m(Separator.groupOpen),
//                                       m(Separator.groupClose)
                               ))),
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

                                  return bt.addChild(t);
                              }, StatementDefiner.definition)),
                      m(Separator.blockClose)
        );
    }
}
