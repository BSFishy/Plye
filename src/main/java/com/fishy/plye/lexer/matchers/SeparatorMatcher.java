package com.fishy.plye.lexer.matchers;

import com.fishy.plye.api.annotations.MatcherPriority;
import com.fishy.plye.api.annotations.Priority;
import com.fishy.plye.api.context.LexContext;
import com.fishy.plye.api.data.lexer.MatchReturnData;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.match.Matcher;
import com.fishy.plye.lexer.tokens.Separator;

import java.util.List;

public class SeparatorMatcher
{
    public static final BlockOpen  blockOpen  = new BlockOpen();
    public static final BlockClose blockClose = new BlockClose();

    public static final CollectionOpen  collectionOpen  = new CollectionOpen();
    public static final CollectionClose collectionClose = new CollectionClose();

    public static final GroupOpen  groupOpen  = new GroupOpen();
    public static final GroupClose groupClose = new GroupClose();

    public static final Semicolon semicolon = new Semicolon();
    public static final Period period = new Period();

    public static void addDefaultMatches()
    {
        addDefaultMatches(LexerApi.getMatches());
    }

    public static void addDefaultMatches(List<Matcher> list)
    {
        LexerApi.addMatches(list, new Matcher[] {
                blockOpen, blockClose,
                collectionOpen, collectionClose,
                groupOpen, groupClose,
                semicolon, period
        });
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class BlockOpen extends Matcher<Separator.BlockOpen>
    {
        public BlockOpen()
        {
            super(Separator.blockOpen);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('{'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class BlockClose extends Matcher<Separator.BlockClose>
    {
        public BlockClose()
        {
            super(Separator.blockClose);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('}'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class CollectionOpen extends Matcher<Separator.CollectionOpen>
    {
        public CollectionOpen()
        {
            super(Separator.collectionOpen);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('['));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class CollectionClose extends Matcher<Separator.CollectionClose>
    {
        public CollectionClose()
        {
            super(Separator.collectionClose);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m(']'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class GroupOpen extends Matcher<Separator.GroupOpen>
    {
        public GroupOpen()
        {
            super(Separator.groupOpen);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('('));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class GroupClose extends Matcher<Separator.GroupClose>
    {
        public GroupClose()
        {
            super(Separator.groupClose);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m(')'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Semicolon extends Matcher<Separator.Semicolon>
    {
        public Semicolon()
        {
            super(Separator.semicolon);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m(';'));
        }
    }

    @MatcherPriority(priority = Priority.Normal)
    public static class Period extends Matcher<Separator.Period>
    {
        public Period()
        {
            super(Separator.period);
        }

        @Override
        public MatchReturnData run(LexContext context)
        {
            return match(context, m('.'));
        }
    }
}
