package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.data.MatchReturnData;
import com.fishy.provalang.api.lexerNew.match.Match;
import com.fishy.provalang.api.lexerNew.match.Matcher;
import com.fishy.provalang.lexer.tokens.OpAssignOperator;

import java.util.List;

public class OpAssignOperatorMatcher
{
    public static final Match<Add> add = Match.of(new Add());
    public static final Match<Subtract> subtract = Match.of(new Subtract());
    public static final Match<Multiply> multiply = Match.of(new Multiply());
    public static final Match<Divide> divide = Match.of(new Divide());

    public static final Match<ShiftLeft> shiftLeft = Match.of(new ShiftLeft());
    public static final Match<ShiftRight> shiftRight = Match.of(new ShiftRight());

    public static final Match<And> and = Match.of(new And());
    public static final Match<Or> or = Match.of(new Or());
    public static final Match<Xor> xor = Match.of(new Xor());

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Match> list)
    {
        LexerApi.addMatches(list, new Match[] {
                add, subtract, multiply, divide,
                shiftLeft, shiftRight,
                and, or, xor
        });
    }

    public static class Add extends Matcher<OpAssignOperator.Add> {
        public Add()
        {
            super(OpAssignOperator.add);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('+'), m('='));
        }
    }

    public static class Subtract extends Matcher<OpAssignOperator.Subtract> {
        public Subtract()
        {
            super(OpAssignOperator.subtract);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('-'), m('='));
        }
    }

    public static class Multiply extends Matcher<OpAssignOperator.Multiply> {
        public Multiply()
        {
            super(OpAssignOperator.multiply);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('*'), m('='));
        }
    }

    public static class Divide extends Matcher<OpAssignOperator.Divide> {
        public Divide()
        {
            super(OpAssignOperator.divide);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('/'), m('='));
        }
    }

    public static class ShiftLeft extends Matcher<OpAssignOperator.ShiftLeft> {
        public ShiftLeft()
        {
            super(OpAssignOperator.shiftLeft);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('<'), m('<'), m('='));
        }
    }

    public static class ShiftRight extends Matcher<OpAssignOperator.ShiftRight> {
        public ShiftRight()
        {
            super(OpAssignOperator.shiftRight);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('<'), m('<'), m('='));
        }
    }

    public static class And extends Matcher<OpAssignOperator.And> {
        public And()
        {
            super(OpAssignOperator.and);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('&'), m('='));
        }
    }

    public static class Or extends Matcher<OpAssignOperator.Or> {
        public Or()
        {
            super(OpAssignOperator.or);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('|'), m('='));
        }
    }

    public static class Xor extends Matcher<OpAssignOperator.Xor> {
        public Xor()
        {
            super(OpAssignOperator.xor);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('^'), m('='));
        }
    }
}
