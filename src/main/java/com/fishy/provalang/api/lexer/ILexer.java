package com.fishy.provalang.api.lexer;

import java.util.List;

public interface ILexer // TODO: make it step as tokens are needed
{
    List<LexerToken> lex(String code);
}
