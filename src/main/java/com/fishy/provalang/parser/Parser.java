package com.fishy.provalang.parser;

import com.fishy.provalang.api.lexer.LexerToken;
import com.fishy.provalang.api.parser.AbstractParser;
import com.fishy.provalang.ast.Ast;

import java.util.List;

public class Parser extends AbstractParser
{
    @Override
    public Ast parse(List<LexerToken> tokens)
    {
        return visit();
    }
}
