package com.fishy.provalang.lexer;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.file.Program;
import com.fishy.provalang.api.lexerNew.AbstractLexer;
import com.fishy.provalang.api.lexerNew.LexToken;

import java.io.IOException;

public class LexerNew extends AbstractLexer
{
    @Override
    public void lex(Program program)
    {
        prepare(program.reader);

        while(canStep())
        {
            LexToken token = null;
            try
            {
                token = step();
            }
            catch (IOException e)
            {
                System.out.println("t");
                ProvalangApi.error("Unable to read file (%s): %s", program.filename, e.getMessage());
            }

            System.out.println(token.toString());
            program.getTokens().add(token);
        }
    }
}
