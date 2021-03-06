////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.lexer;

import com.fishy.plye.file.ProgramFile;

public interface ILexer
{
    void lex(ProgramFile programFile);
}
