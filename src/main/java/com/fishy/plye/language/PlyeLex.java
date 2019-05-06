////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.language;

import com.fishy.plye.api.language.Lex;
import com.fishy.plye.api.lexer.LexerApi;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlyeLex extends Lex
{
    public PlyeLex()
    {
        super(PlyeLanguage.NAME);
    }

    @Override
    public void tokens() {
        LexerApi.addDefaultTokens();
    }

    @Override
    public void matchers() {
        LexerApi.addDefaultMatches();
    }
}
