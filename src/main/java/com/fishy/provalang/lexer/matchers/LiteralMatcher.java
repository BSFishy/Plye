package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.data.MatchReturnData;
import com.fishy.provalang.api.lexerNew.match.Match;
import com.fishy.provalang.api.lexerNew.match.Matcher;
import com.fishy.provalang.lexer.tokens.Literal;

import java.util.List;

public class LiteralMatcher
{
    public static final Match<Integer> integer    = Match.of(new Integer());
    public static final Match<Byte>    byteMatch  = Match.of(new Byte());
    public static final Match<Short>   shortMatch = Match.of(new Short());
    public static final Match<Long>    longMatch  = Match.of(new Long());

    public static final Match<Float>  floatMatch  = Match.of(new Float());
    public static final Match<Double> doubleMatch = Match.of(new Double());

    public static final Match<Boolean> booleanMatch = Match.of(new Boolean());

    public static final Match<String>    string    = Match.of(new String());
    public static final Match<Character> character = Match.of(new Character());

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Match> list)
    {
        LexerApi.addMatches(list, new Match[]{
                integer, byteMatch, shortMatch, longMatch,
                floatMatch, doubleMatch,
                booleanMatch,
                string, character
        });
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Integer extends Matcher<Literal.Integer>
    {
        public Integer()
        {
            super(Literal.integer);
        }

        @Override
        public MatchReturnData run()
        {
            return match(mor(
                    m(moptional(m('-')), mwhile(number())),
                    m(m('0'), mor(
                            m(manyCase('x'), mwhile(mor(number(), minRange(0x41, 0x46), minRange(0x61, 0x66)))),
                            m(manyCase('b'), mwhile(mor(m('0'), m('1'))))))
            ));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Byte extends Matcher<Literal.Byte>
    {
        public Byte()
        {
            super(Literal.byteToken);
        }

        @Override
        public MatchReturnData run()
        {
            return match(mor(
                    m(moptional(m('-')), mwhile(number())),
                    m(m('0'), mor(
                            m(manyCase('x'), mwhile(mor(number(), minRange(0x41, 0x46), minRange(0x61, 0x66)))),
                            m(manyCase('b'), mwhile(mor(m('0'), m('1'))))))
            ));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Short extends Matcher<Literal.Short>
    {
        public Short()
        {
            super(Literal.shortToken);
        }

        @Override
        public MatchReturnData run()
        {
            return match(mor(
                    m(moptional(m('-')), mwhile(number())),
                    m(m('0'), mor(
                            m(manyCase('x'), mwhile(mor(number(), minRange(0x41, 0x46), minRange(0x61, 0x66)))),
                            m(manyCase('b'), mwhile(mor(m('0'), m('1'))))))
            ));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Long extends Matcher<Literal.Long>
    {
        public Long()
        {
            super(Literal.longToken);
        }

        @Override
        public MatchReturnData run()
        {
            return match(mor(
                    m(moptional(m('-')), mwhile(number()), moptional(manyCase('l'))),
                    m(m('0'), mor(
                            m(manyCase('x'), mwhile(mor(number(), minRange(0x41, 0x46), minRange(0x61, 0x66)))),
                            m(manyCase('b'), mwhile(mor(m('0'), m('1'))))))
            ));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Float extends Matcher<Literal.Float>
    {
        public Float()
        {
            super(Literal.floatToken);
        }

        @Override
        public MatchReturnData run()
        {
            return match(moptional(m('-')), mor(
                    mwhile(number()),
                    m(mwhile(number()), m('.'), mwhile(number())),
                    m(m('.'), mwhile(number())),
                    m(mwhile(number()), m('.'))
            ), moptional(m(manyCase('e'), moptional(m('-')), mwhile(number()))), moptional(manyCase('f')));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Double extends Matcher<Literal.Double>
    {
        public Double()
        {
            super(Literal.doubleToken);
        }

        @Override
        public MatchReturnData run()
        {
            return match(moptional(m('-')), mor(
                    mwhile(number()),
                    m(mwhile(number()), m('.'), mwhile(number())),
                    m(m('.'), mwhile(number())),
                    m(mwhile(number()), m('.'))
            ), moptional(m(manyCase('e'), moptional(m('-')), mwhile(number()))), moptional(manyCase('d')));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Boolean extends Matcher<Literal.Boolean>
    {
        public Boolean()
        {
            super(Literal.booleanToken);
        }

        @Override
        public MatchReturnData run()
        {
            return match(mor(m("true"), m("false")), mnot(identifierChar()));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class String extends Matcher<Literal.String>
    {
        public String()
        {
            super(Literal.string);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('"'), mwhile(mor(
                    mnot('"'),
                    mand(mlookbehind(m('\\')), m('"'))
            )), m('"'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Character extends Matcher<Literal.Character>
    {
        public Character()
        {
            super(Literal.character);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('\''), mor(
                    mnot('\''),
                    m(m('\''), mnot('\'')),
                    m(m('\''), m('u'), mfor(4, number()))
            ), m('\''));
        }
    }
}
