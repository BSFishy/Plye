package com.fishy.provalang.api.lexerNew;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.file.FileReader;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public abstract class AbstractLexer implements ILexer
{

    private boolean prepared = false;

    private FileReader reader;

    private LexerTokenInfo info = new LexerTokenInfo();

    protected void prepare(FileReader reader)
    {
        this.reader = reader;

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

        while (!reader.eof())
        {
            c = reader.read();

            System.out.println("Char: " + c);

            buffer.add(c);


        }

        return new LexToken(null, null);
    }

    public boolean canStep()
    {
        return !reader.eof();
    }
}
