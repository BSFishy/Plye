////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.language;

import com.fishy.plye.api.lexer.ILexer;
import com.fishy.plye.api.parser.Parser;
import com.fishy.plye.file.ProgramFile;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public abstract class Language
{
    private final String name;
    private final String extension;

    private final Lex    lex;
    private final ILexer lexer;

    private final Parse  parse;
    private final Parser parser;

    public void lexer()
    {
        lex.tokens();
        lex.matchers();
    }

    public void lex(@NotNull ProgramFile file)
    {
        if(file.shouldLex())
        {
            lexer.lex(file);
            file.finalizeLex();
        }
    }

    public void parser()
    {
        parse.passes();
    }

    public void parse(@NotNull ProgramFile file)
    {
        if(file.shouldParse())
        {
            parser.parse(file, parse.getPassHandler());
            file.finalizeParse();
        }
    }

    public String getExt()
    {
        return extension;
    }
}
