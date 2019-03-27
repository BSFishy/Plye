package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.data.MatchReturnData;
import com.fishy.provalang.api.lexer.match.Matcher;
import com.fishy.provalang.lexer.tokens.OpAssignOperator;

import java.util.List;

public class OpAssignOperatorMatcher
{
    public static final Add add = new Add();
    public static final Subtract subtract = new Subtract();
    public static final Multiply multiply = new Multiply();
    public static final Divide divide = new Divide();

    public static final ShiftLeft shiftLeft = new ShiftLeft();
    public static final ShiftRight shiftRight = new ShiftRight();

    public static final And and = new And();
    public static final Or or = new Or();
    public static final Xor xor = new Xor();

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Matcher> list)
    {
        LexerApi.addMatches(list, new Matcher[] {
                add, subtract, multiply, divide,
                shiftLeft, shiftRight,
                and, or, xor
        });
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Add extends Matcher<OpAssignOperator.Add> {
        public Add()
        {
            super(OpAssignOperator.add);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('+'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Subtract extends Matcher<OpAssignOperator.Subtract> {
        public Subtract()
        {
            super(OpAssignOperator.subtract);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('-'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Multiply extends Matcher<OpAssignOperator.Multiply> {
        public Multiply()
        {
            super(OpAssignOperator.multiply);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('*'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Divide extends Matcher<OpAssignOperator.Divide> {
        public Divide()
        {
            super(OpAssignOperator.divide);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('/'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class ShiftLeft extends Matcher<OpAssignOperator.ShiftLeft> {
        public ShiftLeft()
        {
            super(OpAssignOperator.shiftLeft);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('<'), m('<'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class ShiftRight extends Matcher<OpAssignOperator.ShiftRight> {
        public ShiftRight()
        {
            super(OpAssignOperator.shiftRight);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('<'), m('<'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class And extends Matcher<OpAssignOperator.And> {
        public And()
        {
            super(OpAssignOperator.and);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('&'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Or extends Matcher<OpAssignOperator.Or> {
        public Or()
        {
            super(OpAssignOperator.or);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('|'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Xor extends Matcher<OpAssignOperator.Xor> {
        public Xor()
        {
            super(OpAssignOperator.xor);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('^'), m('='));
        }
    }
}
