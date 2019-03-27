package com.fishy.provalang.api.parser;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.lexer.LexToken;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
public abstract class AbstractParser implements IParser
{
    protected List<LexToken> tokens;
    private boolean          prepared = false;

    public void prepare(List<LexToken> tokens)
    {
        this.tokens = tokens;

        ParserApi.addDefaultVisitors();

        prepared = true;
    }

    // TODO: make visit methods

    private void checkPrepared()
    {
        if(!prepared)
            ProvalangApi.error("Parser must be prepared before visiting");
    }
}
