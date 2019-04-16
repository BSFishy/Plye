package com.fishy.provalang.parser.pass.containerization;

import com.fishy.provalang.api.data.parser.PassResult;
import com.fishy.provalang.api.parser.pass.ParserPass;
import com.fishy.provalang.parser.pass.containerization.definer.BlockDefiner;
import com.fishy.provalang.parser.pass.containerization.definer.StatementDefiner;
import com.fishy.provalang.parser.pass.containerization.token.ContainerizationToken;

import java.util.List;

public class ContainerizationPass extends ParserPass<ContainerizationToken, LexerToken>
{
    public static final ContainerizationPass instance = new ContainerizationPass();

    public ContainerizationPass()
    {
        super(null, LexerToken.class);
    }

    @Override
    public void defaultDefiners()
    {
        addDefiner(StatementDefiner.definition);
        addDefiner(BlockDefiner.definition);
    }

    @Override
    public PassResult<ContainerizationToken> parse(List<LexerToken> tokens)
    {
        return run(tokens);
    }
}
