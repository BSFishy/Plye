package com.fishy.provalang.api.lexer.match;

import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.lexer.data.MatchData;

public interface MatchMethod
{
    MatchData match(LexContext context, int index);
}
