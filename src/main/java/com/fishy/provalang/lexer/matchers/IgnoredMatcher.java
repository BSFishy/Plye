package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.Priority;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.data.MatchReturnData;
import com.fishy.provalang.api.lexer.match.Matcher;
import com.fishy.provalang.lexer.tokens.Ignored;

import java.util.List;

public class IgnoredMatcher
{
    public static final Return         returnMatch    = new Return();
    public static final CarriageReturn carriageReturn = new CarriageReturn();
    public static final Space          space          = new Space();

    public static void addDefaultMatchers()
    {
        addDefaultMatchers(LexerApi.getMatches());
    }

    public static void addDefaultMatchers(List<Matcher> list)
    {
        LexerApi.addMatches(list, new Matcher[]{returnMatch, carriageReturn, space});
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Return extends Matcher<Ignored.Return>
    {

        public Return()
        {
            super(Ignored.returnToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('\n'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class CarriageReturn extends Matcher<Ignored.CarriageReturn>
    {

        public CarriageReturn()
        {
            super(Ignored.carriageReturn);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('\r'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Space extends Matcher<Ignored.Space>
    {

        public Space()
        {
            super(Ignored.space);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m(' '));
        }
    }
}
