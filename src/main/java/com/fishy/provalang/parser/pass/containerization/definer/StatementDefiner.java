package com.fishy.provalang.parser.pass.containerization.definer;

import com.fishy.provalang.api.parser.definer.Definition;
import com.fishy.provalang.lexer.tokens.Separator;
import com.fishy.provalang.parser.pass.containerization.LexerToken;
import com.fishy.provalang.parser.pass.containerization.token.ContainerizationToken;
import com.fishy.provalang.parser.pass.containerization.token.StatementToken;
import org.jetbrains.annotations.NotNull;

public class StatementDefiner extends ContainerizationDefiner
{
    public static final StatementDefiner                              instance   = new StatementDefiner();
    public static final Definition<ContainerizationToken, LexerToken> definition = instance.define();

    @Override
    public @NotNull Definition<ContainerizationToken, LexerToken> define()
    {
        return define(() -> new StatementToken(null),
                      mwhile(m(ContainerizationToken::addToken,
                               mnot(
                                       m(Separator.semicolon),
                                       m(Separator.blockOpen),
                                       m(Separator.blockClose)
//                                       m(Separator.groupOpen),
//                                       m(Separator.groupClose)
                               ))),
                      m(Separator.semicolon)
        );
    }
}
