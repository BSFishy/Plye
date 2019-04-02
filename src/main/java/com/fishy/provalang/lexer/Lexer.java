package com.fishy.provalang.lexer;

import com.fishy.provalang.api.lexer.AbstractLexer;
import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.file.Program;
import com.fishy.provalang.lexer.tokens.Ignored;
import org.jetbrains.annotations.NotNull;

public class Lexer extends AbstractLexer
{
    @Override
    public void lex(@NotNull Program program)
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
