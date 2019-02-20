package com.fishy.provalang.api.lexerNew;

import com.fishy.provalang.api.lexerNew.data.LexData;

import java.io.InputStream;

public interface ILexer
{
    LexData lex(InputStream input);
}
