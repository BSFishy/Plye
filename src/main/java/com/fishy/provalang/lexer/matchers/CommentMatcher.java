package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.data.MatchReturnData;
import com.fishy.provalang.api.lexer.match.Matcher;

import java.util.List;

import static com.fishy.provalang.lexer.tokens.Comment.*;

public class CommentMatcher
{
    public static final SingleLineMatcher    singleLineMatcher    = new SingleLineMatcher();
    public static final MultiLineMatcher     multiLineMatcher     = new MultiLineMatcher();
    public static final DocumentationMatcher documentationMatcher = new DocumentationMatcher();

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Matcher> list)
    {
        LexerApi.addMatches(list, new Matcher[]{
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('/'), m('/'), muntil(newline()));
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('/'), m('*'), mignoreLookahead(mnot('*')), muntil(m("*/")));
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
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('/'), m('*'), m('*'), muntil(m("*/")));
        }
    }
}
