package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.data.MatchReturnData;
import com.fishy.provalang.api.lexerNew.match.Match;
import com.fishy.provalang.api.lexerNew.match.Matcher;
import com.fishy.provalang.lexer.tokens.UnaryOperator;

import java.util.List;

public class UnaryOperatorMatcher
{
    public static final Match<Increment> increment = Match.of(new Increment());
    public static final Match<Decrement> decrement = Match.of(new Decrement());

    public static final Match<Negate> negate = Match.of(new Negate());
    public static final Match<Not> not = Match.of(new Not());

    public static final Match<NegateBits> negateBits = Match.of(new NegateBits());

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Match> list)
    {
        LexerApi.addMatches(list, new Match[] {
                increment, decrement,
                negate, not,
                negateBits
        });
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Increment extends Matcher<UnaryOperator.Increment>
    {
        public Increment()
        {
            super(UnaryOperator.increment);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('+'), m('+'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Decrement extends Matcher<UnaryOperator.Decrement>
    {
        public Decrement()
        {
            super(UnaryOperator.decrement);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('-'), m('-'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Negate extends Matcher<UnaryOperator.Negate>
    {
        public Negate()
        {
            super(UnaryOperator.negate);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('-'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Not extends Matcher<UnaryOperator.Not>
    {
        public Not()
        {
            super(UnaryOperator.not);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('!'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class NegateBits extends Matcher<UnaryOperator.NegateBits>
    {
        public NegateBits()
        {
            super(UnaryOperator.negateBits);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('~'));
        }
    }
}
