package com.fishy.provalang.lexer;

import com.fishy.provalang.api.file.Program;
import com.fishy.provalang.api.lexerNew.AbstractLexer;
import com.fishy.provalang.api.lexerNew.LexToken;
import com.fishy.provalang.lexer.tokens.Ignored;

public class LexerNew extends AbstractLexer
{
    @Override
    public void lex(Program program)
    {
        prepare(program.reader);

        while(canStep())
        {
            LexToken token = step();

            if(!(token.type instanceof Ignored.Space))
            {
                System.out.println(token.toString());
                program.getTokens().add(token);
            }
        }
    }
}
