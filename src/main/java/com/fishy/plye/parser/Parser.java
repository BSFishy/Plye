package com.fishy.plye.parser;

import com.fishy.plye.api.ast.AstNode;
import com.fishy.plye.api.lexer.LexToken;
import com.fishy.plye.api.parser.AbstractParser;
import com.fishy.plye.parser.pass.containerization.ContainerizationPass;
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
    public List<AstNode> parse(@NotNull List<LexToken> tokens)
    {
        addDefaultPasses();
        preparePasses();
        return run(tokens);
    }
}
