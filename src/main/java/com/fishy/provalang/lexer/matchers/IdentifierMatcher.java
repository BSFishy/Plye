package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.Priority;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.data.lexer.MatchReturnData;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.match.Matcher;
import com.fishy.provalang.lexer.tokens.Identifier;

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
