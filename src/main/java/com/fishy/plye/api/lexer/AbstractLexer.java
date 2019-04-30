////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.lexer;

import com.fishy.plye.api.PlyeApi;
import com.fishy.plye.api.context.LexContext;
import com.fishy.plye.api.data.lexer.LexReturnData;
import com.fishy.plye.api.lexer.info.NullTokenType;
import com.fishy.plye.file.FileReader;
import com.fishy.plye.file.FileWrapper;
import com.fishy.plye.lexer.tokens.Ignored;
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
    private LexContext   context;

    protected void prepare(FileReader reader)
    {
        this.reader = reader;
        this.wrapper = new FileWrapper(reader);
        this.context = new LexContext(this.wrapper);

        LexerApi.prepare();

        this.prepared = true;
    }

    @SuppressWarnings("FeatureEnvy")
    public LexToken step()
    {
        check();

        LexReturnData data = LexerApi.lex(this.context);
        if (data == null)
            return NullTokenType.create(info);

        info.increment(data.length);

        String   buffer = clean(data.length);
        LexToken token  = new LexToken(data.type, data.cast(info.clone(), buffer));

        if (data.type instanceof Ignored.Return)
            info.incrementLine();
        else
            info.incrementColumn(data.length);

        return token;
    }

    protected void check()
    {
        if (!isPrepared()) PlyeApi.error("Lexer was not prepared before running");
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
            PlyeApi.error("Error reading the file", e);
        }
        wrapper.clean(length);

        return output;
    }

    public boolean isPrepared()
    {
        return prepared;
    }

    public boolean canStep()
    {
        return !reader.eof();
    }
}
