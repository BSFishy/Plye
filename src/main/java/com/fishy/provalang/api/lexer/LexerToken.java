package com.fishy.provalang.api.lexer;

import lombok.Data;

@Data
public class LexerToken
{
    private final ILexerTokenType                type;
    private final ILexerTokenType.LexerTokenData data;
}
