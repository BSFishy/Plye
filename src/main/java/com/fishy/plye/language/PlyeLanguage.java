////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.language;

import com.fishy.plye.api.language.Language;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.lexer.Lexer;
import com.fishy.plye.parser.Parser;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlyeLanguage extends Language
{
    public static final String NAME = "Plye";
    public static final String EXTENSION = "plye";

    public PlyeLanguage()
    {
        super(NAME, EXTENSION, new PlyeLex(), new Lexer(), new PlyeParse(), new Parser());
    }

    @Override
    public void lexer()
    {
        super.lexer();
        LexerApi.finalizeMatches();
    }
}
