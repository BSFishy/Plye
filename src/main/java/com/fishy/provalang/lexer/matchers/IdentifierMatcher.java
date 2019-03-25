package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.data.MatchReturnData;
import com.fishy.provalang.api.lexerNew.match.Match;
import com.fishy.provalang.api.lexerNew.match.Matcher;
import com.fishy.provalang.lexer.tokens.Identifier;

import java.util.List;

@MatcherPriority(priority = Priority.Normal)
public class IdentifierMatcher extends Matcher<Identifier>
{
    public static final Match<IdentifierMatcher> instance = Match.of(new IdentifierMatcher());

    public static void addDefaultMatch()
    {
        addDefaultMatch(LexerApi.getMatches());
    }

    public static void addDefaultMatch(List<Match> list)
    {
        LexerApi.addMatch(list, instance);
    }

    public IdentifierMatcher()
    {
        super(Identifier.instance);
    }

    @Override
    public MatchReturnData run()
    {
        return match(identifier());
    }
}
