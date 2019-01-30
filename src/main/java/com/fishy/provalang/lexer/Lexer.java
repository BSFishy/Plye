package com.fishy.provalang.lexer;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.lexer.AbstractLexer;
import com.fishy.provalang.api.lexer.LexerToken;
import com.fishy.provalang.api.lexer.types.Ignored;

import java.util.ArrayList;
import java.util.List;

public class Lexer extends AbstractLexer
{
    @Override
    public List<LexerToken> lex(String code)
    {
        List<LexerToken> output = new ArrayList<>();
        prepareCode(code);

        while (canStep())
        {
            LexerToken step = step();

            if (step.getType() == null)
            {
//                ProvalangApi.error("Error lexing: Unexpected character at line %d, column %d:\n\t%s\n\t%s%s\n", step.getData().getInfo().line + 1,
//                                   step.getData().getInfo().column + 1, code.split("\n")[step.getData().getInfo().line],
//                                   Utils.repeat(" ", step.getData().getInfo().column), Utils.repeat("^", step.getData().getInfo().length));
                ProvalangApi.errorPretty("Error lexing: Unexpected character", step, code);
            }

            if (!(step.getType() instanceof Ignored.Space))
                output.add(step);
        }

        return output;
    }
}
