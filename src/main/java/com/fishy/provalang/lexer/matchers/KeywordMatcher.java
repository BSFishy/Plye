package com.fishy.provalang.lexer.matchers;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.data.MatchReturnData;
import com.fishy.provalang.api.lexer.match.Match;
import com.fishy.provalang.api.lexer.match.Matcher;
import com.fishy.provalang.lexer.tokens.Keyword;

import java.util.List;

public class KeywordMatcher
{
    public static final Match<New>    newMatch    = Match.of(new New());
    public static final Match<Class>  classMatch  = Match.of(new Class());
    public static final Match<Static> staticMatch = Match.of(new Static());

    public static final Match<Package> packageMatch = Match.of(new Package());
    public static final Match<Import>  importMatch  = Match.of(new Import());

    public static final Match<Public>    publicMatch    = Match.of(new Public());
    public static final Match<Protected> protectedMatch = Match.of(new Protected());
    public static final Match<Private>   privateMatch   = Match.of(new Private());

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Match> list)
    {
        LexerApi.addMatches(list, new Match[]{
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
        public MatchReturnData run()
        {
            return match(m("new"), mignoreLookahead(mnot(identifierChar())));
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
        public MatchReturnData run()
        {
            return match(m("class"), mignoreLookahead(mnot(identifierChar())));
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
        public MatchReturnData run()
        {
            return match(m("static"), mignoreLookahead(mnot(identifierChar())));
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
        public MatchReturnData run()
        {
            return match(m("package"), mignoreLookahead(mnot(identifierChar())));
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
        public MatchReturnData run()
        {
            return match(m("import"), mignoreLookahead(mnot(identifierChar())));
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
        public MatchReturnData run()
        {
            return match(m("public"), mignoreLookahead(mnot(identifierChar())));
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
        public MatchReturnData run()
        {
            return match(m("protected"), mignoreLookahead(mnot(identifierChar())));
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
        public MatchReturnData run()
        {
            return match(m("private"), mignoreLookahead(mnot(identifierChar())));
        }
    }
}
