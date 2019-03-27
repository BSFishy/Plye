package com.fishy.provalang.api.lexer;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.data.LexReturnData;
import com.fishy.provalang.api.lexer.info.NullTokenType;
import com.fishy.provalang.file.FileReader;
import com.fishy.provalang.file.FileWrapper;
import com.fishy.provalang.lexer.tokens.Ignored;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.IOException;

@ToString
@EqualsAndHashCode
public abstract class AbstractLexer implements ILexer
{

    private boolean prepared = false;

    private FileReader  reader;
    private FileWrapper wrapper;

    private LexTokenInfo info = new LexTokenInfo();
    private LexContext context;

    protected void prepare(FileReader reader)
    {
        this.reader = reader;
        this.wrapper = new FileWrapper(reader);
        this.context = new LexContext(this.wrapper);

        LexerApi.addDefaultTokens();
        LexerApi.addDefaultMatches();

        LexerApi.finalizeMatches();

        this.prepared = true;
    }

    public boolean isPrepared()
    {
        return prepared;
    }

    protected void check()
    {
        if (!isPrepared()) ProvalangApi.error("Lexer was not prepared before running");
    }

    public LexToken step()
    {
        check();

        LexReturnData data = LexerApi.lex(this.context);
        if(data == null)
            return NullTokenType.create(info);

        info.increment(data.length);

        String buffer = clean(data.length);
        LexToken token = new LexToken(data.type, data.cast(info.clone(), buffer));

        if(data.type instanceof Ignored.Return)
            info.incrementLine();
        else
            info.incrementColumn(data.length);

        return token;
    }

    public boolean canStep()
    {
        return !reader.eof();
    }

    protected String clean(int length)
    {
        String output = "";
        try
        {
            output = wrapper.readLength(length);
        }
        catch (IOException e)
        {
            ProvalangApi.error("Error reading the file", e);
        }
        wrapper.clean(length);

        return output;
    }
}
