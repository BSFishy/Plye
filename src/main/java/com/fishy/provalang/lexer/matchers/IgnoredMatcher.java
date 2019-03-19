package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.data.MatchReturnData;
import com.fishy.provalang.api.lexerNew.match.Match;
import com.fishy.provalang.api.lexerNew.match.Matcher;
import com.fishy.provalang.lexer.tokens.Ignored;

import java.util.List;

public class IgnoredMatcher
{
    public static final Match<Return>         returnMatch    = Match.of(new Return());
    public static final Match<CarriageReturn> carriageReturn = Match.of(new CarriageReturn());
    public static final Match<Space>          space          = Match.of(new Space());

    public static void addDefaultMatchers()
    {
        addDefaultMatchers(LexerApi.getMatches());
    }

    public static void addDefaultMatchers(List<Match> list)
    {
        LexerApi.addMatches(list, new Match[]{returnMatch, carriageReturn, space});
    }

    public static class Return extends Matcher<Ignored.Return>
    {

        public Return()
        {
            super(Ignored.returnToken);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('\n'));
        }
    }

    public static class CarriageReturn extends Matcher<Ignored.CarriageReturn>
    {

        public CarriageReturn()
        {
            super(Ignored.carriageReturn);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('\r'));
        }
    }

    public static class Space extends Matcher<Ignored.Space>
    {

        public Space()
        {
            super(Ignored.space);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m(' '));
        }
    }
}
