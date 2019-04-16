package com.fishy.provalang.parser;

import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.api.parser.AbstractParser;
import com.fishy.provalang.api.parser.pass.PassToken;
import com.fishy.provalang.parser.pass.containerization.ContainerizationPass;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Parser extends AbstractParser
{
    @Override
    public void addDefaultPasses()
    {
        addPass(ContainerizationPass.instance);
    }

    @Override
    public <T extends PassToken> List<T> parse(@NotNull List<LexToken> tokens)
    {
        addDefaultPasses();
        preparePasses();
        return run(tokens);
    }
}
