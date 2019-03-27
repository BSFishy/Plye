package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.data.MatchReturnData;
import com.fishy.provalang.api.lexer.match.Matcher;
import com.fishy.provalang.lexer.tokens.Separator;

import java.util.List;

public class SeparatorMatcher
{
    public static final CurlyBracketOpen curlyBracketOpen = new CurlyBracketOpen();
    public static final CurlyBracketClose curlyBracketClose = new CurlyBracketClose();

    public static final SquareBracketOpen squareBracketOpen = new SquareBracketOpen();
    public static final SquareBracketClose squareBracketClose = new SquareBracketClose();

    public static final ParenthesisOpen parenthesisOpen = new ParenthesisOpen();
    public static final ParenthesisClose parenthesisClose = new ParenthesisClose();

    public static final Semicolon semicolon = new Semicolon();
    public static final Period period = new Period();

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Matcher> list)
    {
        LexerApi.addMatches(list, new Matcher[] {
                curlyBracketOpen, curlyBracketClose,
                squareBracketOpen, squareBracketClose,
                parenthesisOpen, parenthesisClose,
                semicolon, period
        });
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class CurlyBracketOpen extends Matcher<Separator.CurlyBracketOpen>
    {
        public CurlyBracketOpen()
        {
            super(Separator.curlyBracketOpen);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('{'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class CurlyBracketClose extends Matcher<Separator.CurlyBracketClose>
    {
        public CurlyBracketClose()
        {
            super(Separator.curlyBracketClose);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('}'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class SquareBracketOpen extends Matcher<Separator.SquareBracketOpen>
    {
        public SquareBracketOpen()
        {
            super(Separator.squareBracketOpen);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('['));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class SquareBracketClose extends Matcher<Separator.SquareBracketClose>
    {
        public SquareBracketClose()
        {
            super(Separator.squareBracketClose);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m(']'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class ParenthesisOpen extends Matcher<Separator.ParenthesisOpen>
    {
        public ParenthesisOpen()
        {
            super(Separator.parenthesisOpen);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('('));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class ParenthesisClose extends Matcher<Separator.ParenthesisClose>
    {
        public ParenthesisClose()
        {
            super(Separator.parenthesisClose);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m(')'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Semicolon extends Matcher<Separator.Semicolon>
    {
        public Semicolon()
        {
            super(Separator.semicolon);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m(';'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Period extends Matcher<Separator.Period>
    {
        public Period()
        {
            super(Separator.period);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('.'));
        }
    }
}
