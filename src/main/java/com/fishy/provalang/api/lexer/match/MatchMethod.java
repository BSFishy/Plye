package com.fishy.provalang.api.lexer.match;

import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.data.lexer.MatchData;
import com.fishy.provalang.api.matching.IMethod;

@FunctionalInterface
public interface MatchMethod extends IMethod<MatchData, LexContext>
{
}
