package com.fishy.provalang.api.lexer.match;

import com.fishy.provalang.api.lexer.data.MatchData;

public interface MatchMethod
{
    MatchData match(int index);
}
