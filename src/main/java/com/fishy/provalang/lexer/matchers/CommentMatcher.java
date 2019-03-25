package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.data.MatchReturnData;
import com.fishy.provalang.api.lexer.match.Match;
import com.fishy.provalang.api.lexer.match.Matcher;

import java.util.List;

import static com.fishy.provalang.lexer.tokens.Comment.*;

public class CommentMatcher
{
    public static final Match<SingleLineMatcher>    singleLineMatcher    = Match.of(new SingleLineMatcher());
    public static final Match<MultiLineMatcher>     multiLineMatcher     = Match.of(new MultiLineMatcher());
    public static final Match<DocumentationMatcher> documentationMatcher = Match.of(new DocumentationMatcher());

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Match> list)
    {
        LexerApi.addMatches(list, new Match[]{
                singleLineMatcher,
                multiLineMatcher,
                documentationMatcher
        });
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class SingleLineMatcher extends Matcher<SingleLine>
    {
        public SingleLineMatcher()
        {
            super(singleLine);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('/'), m('/'), muntil(newline()));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class MultiLineMatcher extends Matcher<MultiLine>
    {
        public MultiLineMatcher()
        {
            super(multiLine);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('/'), m('*'), mignoreLookahead(mnot('*')), muntil(m("*/")));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class DocumentationMatcher extends Matcher<Documentation>
    {
        public DocumentationMatcher()
        {
            super(documentation);
        }

        @Override
        public MatchReturnData run()
        {
            return match(m('/'), m('*'), m('*'), muntil(m("*/")));
        }
    }
}
