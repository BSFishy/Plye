////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.lexer.matchers;

import com.fishy.plye.api.annotations.MatcherPriority;
import com.fishy.plye.api.annotations.Priority;
import com.fishy.plye.api.context.LexContext;
import com.fishy.plye.api.data.lexer.MatchReturnData;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.match.Matcher;
import com.fishy.plye.lexer.tokens.UnaryOperator;

import java.util.List;

public class UnaryOperatorMatcher
{
    public static final Increment increment = new Increment();
    public static final Decrement decrement = new Decrement();

    public static final Negate negate = new Negate();
    public static final Not    not    = new Not();

    public static final NegateBits negateBits = new NegateBits();

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Matcher> list)
    {
        LexerApi.addMatches(list, new Matcher[]{
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('+'), m('+'));
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('-'), m('-'));
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('-'));
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('!'));
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('~'));
        }
    }
}
