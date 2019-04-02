package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.Priority;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.data.MatchReturnData;
import com.fishy.provalang.api.lexer.match.Matcher;
import com.fishy.provalang.lexer.tokens.Literal;

import java.util.List;

public class LiteralMatcher
{
    public static final Integer integer    = new Integer();
    public static final Byte    byteMatch  = new Byte();
    public static final Short   shortMatch = new Short();
    public static final Long    longMatch  = new Long();

    public static final Float  floatMatch  = new Float();
    public static final Double doubleMatch = new Double();

    public static final Boolean booleanMatch = new Boolean();

    public static final String    string    = new String();
    public static final Character character = new Character();

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Matcher> list)
    {
        LexerApi.addMatches(list, new Matcher[]{
                integer, byteMatch, shortMatch, longMatch,
                floatMatch, doubleMatch,
                booleanMatch,
                string, character
        });
    }

    @MatcherPriority(priority = Priority.High)
    public static class Integer extends Matcher<Literal.Integer>
    {
        public Integer()
        {
            super(Literal.integer);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, mor(
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, mor(
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, mor(
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, mor(
                    m(moptional(m('-')), mwhile(number()), moptional(manyCase('l'))),
                    m(m('0'), mor(
                            m(manyCase('x'), mwhile(mor(number(), minRange(0x41, 0x46), minRange(0x61, 0x66)))),
                            m(manyCase('b'), mwhile(mor(m('0'), m('1'))))))
            ));
        }
    }

    @MatcherPriority(priority = Priority.Normal, overrides = {Double.class})
    public static class Float extends Matcher<Literal.Float>
    {
        public Float()
        {
            super(Literal.floatToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, moptional(m('-')), mor(
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, moptional(m('-')), mor(
                    mwhile(number()),
                    m(mwhile(number()), m('.'), mwhile(number())),
                    m(m('.'), mwhile(number())),
                    m(mwhile(number()), m('.'))
            ), moptional(m(manyCase('e'), moptional(m('-')), mwhile(number()))), moptional(manyCase('d')));
        }
    }

    @MatcherPriority(priority = Priority.High)
    public static class Boolean extends Matcher<Literal.Boolean>
    {
        public Boolean()
        {
            super(Literal.booleanToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, mor(m("true"), m("false")), mnot(identifierChar()));
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('"'), mwhile(mor(
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('\''), mor(
                    mnot('\''),
                    m(m('\''), mnot('\'')),
                    m(m('\''), m('u'), mfor(4, number()))
            ), m('\''));
        }
    }
}
