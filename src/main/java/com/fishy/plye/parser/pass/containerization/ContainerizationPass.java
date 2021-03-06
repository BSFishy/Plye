////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.parser.pass.containerization;

import com.fishy.plye.api.data.parser.PassResult;
import com.fishy.plye.api.parser.pass.ParserPass;
import com.fishy.plye.parser.pass.containerization.definer.ContainerizationDefiner;
import com.fishy.plye.parser.pass.containerization.token.ContainerizationToken;

import java.util.List;

public class ContainerizationPass extends ParserPass<ContainerizationToken, LexerToken>
{
    public static final ContainerizationPass instance = new ContainerizationPass();

    @Override
    public void defaultDefiners()
    {
        addDefiner(ContainerizationDefiner.instance.getDefinition());
    }

    @Override
    public PassResult<ContainerizationToken> parse(List<LexerToken> tokens)
    {
        return run(tokens);
    }
}
