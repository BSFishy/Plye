package com.fishy.plye.lexer;

import com.fishy.plye.api.lexer.AbstractLexer;
import com.fishy.plye.api.lexer.LexToken;
import com.fishy.plye.file.Program;
import com.fishy.plye.lexer.tokens.Ignored;
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
