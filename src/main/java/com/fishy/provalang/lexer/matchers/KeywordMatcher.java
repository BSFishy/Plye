package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.Priority;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.data.lexer.MatchReturnData;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.match.Matcher;
import com.fishy.provalang.lexer.tokens.Keyword;

import java.util.List;

public class KeywordMatcher
{
    public static final New    newMatch    = new New();
    public static final Class  classMatch  = new Class();
    public static final Static staticMatch = new Static();

    public static final Package packageMatch = new Package();
    public static final Import  importMatch  = new Import();

    public static final Public    publicMatch    = new Public();
    public static final Protected protectedMatch = new Protected();
    public static final Private   privateMatch   = new Private();

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Matcher> list)
    {
        LexerApi.addMatches(list, new Matcher[]{
                newMatch, classMatch, staticMatch,
                packageMatch, importMatch,
                publicMatch, protectedMatch, privateMatch
        });
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class New extends Matcher<Keyword.New>
    {
        public New()
        {
            super(Keyword.newToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m("new"), mignoreLookahead(mnot(identifierChar())));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Class extends Matcher<Keyword.Class>
    {
        public Class()
        {
            super(Keyword.classToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m("class"), mignoreLookahead(mnot(identifierChar())));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Static extends Matcher<Keyword.Static>
    {
        public Static()
        {
            super(Keyword.staticToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m("static"), mignoreLookahead(mnot(identifierChar())));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Package extends Matcher<Keyword.Package>
    {
        public Package()
        {
            super(Keyword.packageToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m("package"), mignoreLookahead(mnot(identifierChar())));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Import extends Matcher<Keyword.Import>
    {
        public Import()
        {
            super(Keyword.importToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m("import"), mignoreLookahead(mnot(identifierChar())));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Public extends Matcher<Keyword.Public>
    {
        public Public()
        {
            super(Keyword.publicToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m("public"), mignoreLookahead(mnot(identifierChar())));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Protected extends Matcher<Keyword.Protected>
    {
        public Protected()
        {
            super(Keyword.protectedToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m("protected"), mignoreLookahead(mnot(identifierChar())));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Private extends Matcher<Keyword.Private>
    {
        public Private()
        {
            super(Keyword.privateToken);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m("private"), mignoreLookahead(mnot(identifierChar())));
        }
    }
}
