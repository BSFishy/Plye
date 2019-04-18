package com.fishy.plye.lexer.matchers;

import com.fishy.plye.api.annotations.MatcherPriority;
import com.fishy.plye.api.annotations.Priority;
import com.fishy.plye.api.context.LexContext;
import com.fishy.plye.api.data.lexer.MatchReturnData;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.match.Matcher;
import com.fishy.plye.lexer.tokens.Identifier;

import java.util.List;

@MatcherPriority(priority = Priority.Low)
public class IdentifierMatcher extends Matcher<Identifier>
{
    public static final IdentifierMatcher instance = new IdentifierMatcher();

    public static void addDefaultMatch()
    {
        addDefaultMatch(LexerApi.getMatches());
    }

    public static void addDefaultMatch(List<Matcher> list)
    {
        LexerApi.addMatch(list, instance);
    }

    public IdentifierMatcher()
    {
        super(Identifier.instance);
    }

    @Override
    public MatchReturnData run(LexContext context)
    {
        return match(context, identifier());
    }
}
