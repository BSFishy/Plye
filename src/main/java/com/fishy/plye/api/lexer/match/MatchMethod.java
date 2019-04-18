package com.fishy.plye.api.lexer.match;

import com.fishy.plye.api.context.LexContext;
import com.fishy.plye.api.data.lexer.MatchData;
import com.fishy.plye.api.matching.IMethod;

@FunctionalInterface
public interface MatchMethod extends IMethod<MatchData, LexContext>
{
}
