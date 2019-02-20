package com.fishy.provalang.api.lexerNew;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public abstract class AbstractLexer implements ILexer
{

    private boolean prepared = false;

    private DataInputStream input;

    private LexerTokenInfo info = new LexerTokenInfo();

    protected void prepare(InputStream input)
    {
        this.input = new DataInputStream(input);

        this.prepared = true;
    }

    public boolean isPrepared()
    {
        return prepared;
    }

    protected void check()
    {
        if(!isPrepared()) ProvalangApi.error("Lexer was not prepared before running");
    }

    public LexToken step() throws IOException
    {
        check();

        List<Character> buffer = new ArrayList<>();
        char c;

        boolean eof = false;

        while (!eof)
        {
            try{
                c = input.readChar();

                System.out.println("Char: " + c);

                buffer.add(c);
            } catch (EOFException ignored)
            {
                eof = true;
            }
        }

        return new LexToken();
    }

    public boolean canStep() throws IOException
    {
        input.mark(2);
        boolean value = input.read() != -1;
        input.reset();
        return value;
    }
}
