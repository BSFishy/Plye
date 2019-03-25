package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.data.MatchReturnData;
import com.fishy.provalang.api.lexerNew.match.Match;
import com.fishy.provalang.api.lexerNew.match.Matcher;
import com.fishy.provalang.lexer.tokens.Separator;

import java.util.List;

public class SeparatorMatcher
{
    public static final Match<CurlyBracketOpen> curlyBracketOpen = Match.of(new CurlyBracketOpen());
    public static final Match<CurlyBracketClose> curlyBracketClose = Match.of(new CurlyBracketClose());

    public static final Match<SquareBracketOpen> squareBracketOpen = Match.of(new SquareBracketOpen());
    public static final Match<SquareBracketClose> squareBracketClose = Match.of(new SquareBracketClose());

    public static final Match<ParenthesisOpen> parenthesisOpen = Match.of(new ParenthesisOpen());
    public static final Match<ParenthesisClose> parenthesisClose = Match.of(new ParenthesisClose());

    public static final Match<Semicolon> semicolon = Match.of(new Semicolon());
    public static final Match<Period> period = Match.of(new Period());

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Match> list)
    {
        LexerApi.addMatches(list, new Match[] {
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
        public MatchReturnData run()
        {
            return match(m('{'));
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
        public MatchReturnData run()
        {
            return match(m('}'));
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
        public MatchReturnData run()
        {
            return match(m('['));
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
        public MatchReturnData run()
        {
            return match(m(']'));
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
        public MatchReturnData run()
        {
            return match(m('('));
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
        public MatchReturnData run()
        {
            return match(m(')'));
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
        public MatchReturnData run()
        {
            return match(m(';'));
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
        public MatchReturnData run()
        {
            return match(m('.'));
        }
    }
}
