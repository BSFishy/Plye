package com.fishy.provalang.api.parser;

import com.fishy.provalang.api.lexer.LexerToken;
import com.fishy.provalang.ast.Ast;

import java.util.List;

public interface IParser
{
    Ast parse(List<LexerToken> tokens); // TODO: work with lexer stream not list
}
