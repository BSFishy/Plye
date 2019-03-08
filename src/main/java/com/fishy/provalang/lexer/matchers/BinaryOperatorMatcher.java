package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.data.MatchReturnData;
import com.fishy.provalang.api.lexerNew.match.Match;
import com.fishy.provalang.api.lexerNew.match.Matcher;

import java.util.List;

import static com.fishy.provalang.lexer.tokens.BinaryOperator.*;

public class BinaryOperatorMatcher
{
    public static final Match<AssignMatcher>    assignMatch    = Match.of(new AssignMatcher());
    public static final Match<EqualsMatcher>    equalsMatch    = Match.of(new EqualsMatcher());
    public static final Match<NotEqualsMatcher> notEqualsMatch = Match.of(new NotEqualsMatcher());

    public static final Match<AddMatcher>      addMatch      = Match.of(new AddMatcher());
    public static final Match<SubtractMatcher> subtractMatch = Match.of(new SubtractMatcher());
    public static final Match<MultiplyMatcher> multiplyMatch = Match.of(new MultiplyMatcher());
    public static final Match<DivideMatcher>   divideMatch   = Match.of(new DivideMatcher());

    public static final Match<GreaterThanMatcher>       greaterThanMatch       = Match.of(new GreaterThanMatcher());
    public static final Match<LessThanMatcher>          lessThanMatch          = Match.of(new LessThanMatcher());
    public static final Match<GreaterThanEqualsMatcher> greaterThanEqualsMatch = Match.of(new GreaterThanEqualsMatcher());
    public static final Match<LessThanEqualsMatcher>    lessThanEqualsMatch    = Match.of(new LessThanEqualsMatcher());

    public static final Match<AndMatcher> andMatch = Match.of(new AndMatcher());
    public static final Match<OrMatcher>  orMatch  = Match.of(new OrMatcher());
    public static final Match<XorMatcher> xorMatch = Match.of(new XorMatcher());

    public static final Match<BooleanAndMatcher> booleanAndMatch = Match.of(new BooleanAndMatcher());
    public static final Match<BooleanOrMatcher>  booleanOrMatch  = Match.of(new BooleanOrMatcher());

    public static final Match<ModulusMatcher> modulusMatch = Match.of(new ModulusMatcher());

    public static final Match<ShiftLeftMatcher>  shiftLeftMatch  = Match.of(new ShiftLeftMatcher());
    public static final Match<ShiftRightMatcher> shiftRightMatch = Match.of(new ShiftRightMatcher());

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Match> matches)
    {
        LexerApi.addMatches(matches, new Match[] {

        });
    }

    public static class AssignMatcher extends Matcher<Assign>
    {
        public AssignMatcher()
        {
            super(assign);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('='), mnot('='));
        }
    }

    public static class EqualsMatcher extends Matcher<Equals>
    {
        public EqualsMatcher()
        {
            super(equals);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('='), m('='));
        }
    }

    public static class NotEqualsMatcher extends Matcher<NotEquals>
    {
        public NotEqualsMatcher()
        {
            super(notEquals);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('!'), m('='));
        }
    }

    public static class AddMatcher extends Matcher<Add>
    {
        public AddMatcher()
        {
            super(add);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('+'), mnot('='));
        }
    }

    public static class SubtractMatcher extends Matcher<Subtract>
    {
        public SubtractMatcher()
        {
            super(subtract);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('-'), mnot('='));
        }
    }

    public static class MultiplyMatcher extends Matcher<Multiply>
    {
        public MultiplyMatcher()
        {
            super(multiply);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('*'), mnot('='));
        }
    }

    public static class DivideMatcher extends Matcher<Divide>
    {
        public DivideMatcher()
        {
            super(divide);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('/'), mnot('='));
        }
    }

    public static class GreaterThanMatcher extends Matcher<GreaterThan>
    {
        public GreaterThanMatcher()
        {
            super(greaterThan);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('>'), mand(mnot('='), mnot('>')));
        }
    }

    public static class LessThanMatcher extends Matcher<LessThan>
    {
        public LessThanMatcher()
        {
            super(lessThan);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('<'), mand(mnot('='), mnot('<')));
        }
    }

    public static class GreaterThanEqualsMatcher extends Matcher<GreaterThanEquals>
    {
        public GreaterThanEqualsMatcher()
        {
            super(greaterThanEquals);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('>'), m('='));
        }
    }

    public static class LessThanEqualsMatcher extends Matcher<LessThanEquals>
    {
        public LessThanEqualsMatcher()
        {
            super(lessThanEquals);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('<'), m('='));
        }
    }

    public static class AndMatcher extends Matcher<And>
    {
        public AndMatcher()
        {
            super(and);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('&'), mand(mnot('&'), mnot('=')));
        }
    }

    public static class OrMatcher extends Matcher<Or>
    {
        public OrMatcher()
        {
            super(or);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('|'), mand(mnot('|'), mnot('=')));
        }
    }

    public static class XorMatcher extends Matcher<Xor>
    {
        public XorMatcher()
        {
            super(xor);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('&'), mnot('='));
        }
    }

    public static class BooleanAndMatcher extends Matcher<BooleanAnd>
    {
        public BooleanAndMatcher()
        {
            super(booleanAnd);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('&'), m('&'));
        }
    }

    public static class BooleanOrMatcher extends Matcher<BooleanOr>
    {
        public BooleanOrMatcher()
        {
            super(booleanOr);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('|'), m('|'));
        }
    }

    public static class ModulusMatcher extends Matcher<Modulus>
    {
        public ModulusMatcher()
        {
            super(modulus);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('%'), mnot('='));
        }
    }

    public static class ShiftLeftMatcher extends Matcher<ShiftLeft>
    {
        public ShiftLeftMatcher()
        {
            super(shiftLeft);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('<'), m('<'));
        }
    }

    public static class ShiftRightMatcher extends Matcher<ShiftRight>
    {
        public ShiftRightMatcher()
        {
            super(shiftRight);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('>'), m('>'));
        }
    }
}
