package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.Priority;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.data.lexer.MatchReturnData;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.match.Matcher;

import java.util.List;

import static com.fishy.provalang.lexer.tokens.BinaryOperator.*;

public class BinaryOperatorMatcher
{
    public static final AssignMatcher    assignMatch    = new AssignMatcher();
    public static final EqualsMatcher    equalsMatch    = new EqualsMatcher();
    public static final NotEqualsMatcher notEqualsMatch = new NotEqualsMatcher();

    public static final AddMatcher      addMatch      = new AddMatcher();
    public static final SubtractMatcher subtractMatch = new SubtractMatcher();
    public static final MultiplyMatcher multiplyMatch = new MultiplyMatcher();
    public static final DivideMatcher   divideMatch   = new DivideMatcher();

    public static final GreaterThanMatcher       greaterThanMatch       = new GreaterThanMatcher();
    public static final LessThanMatcher          lessThanMatch          = new LessThanMatcher();
    public static final GreaterThanEqualsMatcher greaterThanEqualsMatch = new GreaterThanEqualsMatcher();
    public static final LessThanEqualsMatcher    lessThanEqualsMatch    = new LessThanEqualsMatcher();

    public static final AndMatcher andMatch = new AndMatcher();
    public static final OrMatcher  orMatch  = new OrMatcher();
    public static final XorMatcher xorMatch = new XorMatcher();

    public static final BooleanAndMatcher booleanAndMatch = new BooleanAndMatcher();
    public static final BooleanOrMatcher  booleanOrMatch  = new BooleanOrMatcher();

    public static final ModulusMatcher modulusMatch = new ModulusMatcher();

    public static final ShiftLeftMatcher  shiftLeftMatch  = new ShiftLeftMatcher();
    public static final ShiftRightMatcher shiftRightMatch = new ShiftRightMatcher();

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Matcher> matches)
    {
        LexerApi.addMatches(matches, new Matcher[] {
            assignMatch, equalsMatch, notEqualsMatch,
                addMatch, subtractMatch, multiplyMatch, divideMatch,
                greaterThanMatch, lessThanMatch, greaterThanEqualsMatch, lessThanEqualsMatch,
                andMatch, orMatch, xorMatch,
                booleanAndMatch, booleanOrMatch,
                modulusMatch,
                shiftLeftMatch, shiftRightMatch
        });
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class AssignMatcher extends Matcher<Assign>
    {
        public AssignMatcher()
        {
            super(assign);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('='), mnot('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class EqualsMatcher extends Matcher<Equals>
    {
        public EqualsMatcher()
        {
            super(equals);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('='), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class NotEqualsMatcher extends Matcher<NotEquals>
    {
        public NotEqualsMatcher()
        {
            super(notEquals);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('!'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class AddMatcher extends Matcher<Add>
    {
        public AddMatcher()
        {
            super(add);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('+'), mnot('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class SubtractMatcher extends Matcher<Subtract>
    {
        public SubtractMatcher()
        {
            super(subtract);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('-'), mnot('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class MultiplyMatcher extends Matcher<Multiply>
    {
        public MultiplyMatcher()
        {
            super(multiply);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('*'), mnot('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class DivideMatcher extends Matcher<Divide>
    {
        public DivideMatcher()
        {
            super(divide);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('/'), mnot('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class GreaterThanMatcher extends Matcher<GreaterThan>
    {
        public GreaterThanMatcher()
        {
            super(greaterThan);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('>'), mand(mnot('='), mnot('>')));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class LessThanMatcher extends Matcher<LessThan>
    {
        public LessThanMatcher()
        {
            super(lessThan);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('<'), mand(mnot('='), mnot('<')));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class GreaterThanEqualsMatcher extends Matcher<GreaterThanEquals>
    {
        public GreaterThanEqualsMatcher()
        {
            super(greaterThanEquals);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('>'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class LessThanEqualsMatcher extends Matcher<LessThanEquals>
    {
        public LessThanEqualsMatcher()
        {
            super(lessThanEquals);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('<'), m('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class AndMatcher extends Matcher<And>
    {
        public AndMatcher()
        {
            super(and);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('&'), mand(mnot('&'), mnot('=')));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class OrMatcher extends Matcher<Or>
    {
        public OrMatcher()
        {
            super(or);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('|'), mand(mnot('|'), mnot('=')));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class XorMatcher extends Matcher<Xor>
    {
        public XorMatcher()
        {
            super(xor);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('&'), mnot('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class BooleanAndMatcher extends Matcher<BooleanAnd>
    {
        public BooleanAndMatcher()
        {
            super(booleanAnd);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('&'), m('&'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class BooleanOrMatcher extends Matcher<BooleanOr>
    {
        public BooleanOrMatcher()
        {
            super(booleanOr);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('|'), m('|'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class ModulusMatcher extends Matcher<Modulus>
    {
        public ModulusMatcher()
        {
            super(modulus);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('%'), mnot('='));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class ShiftLeftMatcher extends Matcher<ShiftLeft>
    {
        public ShiftLeftMatcher()
        {
            super(shiftLeft);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('<'), m('<'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class ShiftRightMatcher extends Matcher<ShiftRight>
    {
        public ShiftRightMatcher()
        {
            super(shiftRight);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('>'), m('>'));
        }
    }
}
