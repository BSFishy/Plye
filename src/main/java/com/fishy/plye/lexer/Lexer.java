////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.lexer;

import com.fishy.plye.api.lexer.AbstractLexer;
import com.fishy.plye.api.lexer.LexToken;
import com.fishy.plye.file.ProgramFile;
import com.fishy.plye.lexer.tokens.Ignored;
import org.jetbrains.annotations.NotNull;

public class Lexer extends AbstractLexer
{
    @Override
    public void lex(@NotNull ProgramFile file)
    {
        prepare(file.reader);

        while (canStep())
        {
            LexToken token = step();

            if (!(token.type instanceof Ignored.IgnoredTokenType))
            {
                file.getTokens().add(token);
            }
        }
    }
}
