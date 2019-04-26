package com.fishy.plye.api.lexer;

import com.fishy.plye.api.annotations.MatcherPriority;
import com.fishy.plye.api.context.LexContext;
import com.fishy.plye.api.data.lexer.LexReturnData;
import com.fishy.plye.api.data.lexer.MatchReturnData;
import com.fishy.plye.api.lexer.info.util.MatcherComparator;
import com.fishy.plye.api.lexer.match.Matcher;
import com.fishy.plye.lexer.matchers.*;
import com.fishy.plye.lexer.tokens.*;
import com.fishy.plye.utils.ArrayUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class LexerApi
{
    // LIST STUFF

    private static final List<TokenType> tokenTypes = new ArrayList<>();

    private static final List<Matcher> regularMatches     = new ArrayList<>();
    private static final List<Matcher> replacementMatches = new ArrayList<>();
    private static final List<Matcher> matches            = new ArrayList<>();

    // For tokens

    public static void addTokenType(@NotNull TokenType token)
    {
        addTokenType(tokenTypes, token);
    }

    public static void addTokenType(@NotNull List<TokenType> list, @NotNull TokenType token)
    {
        if (!list.contains(token))
            list.add(token);
    }

    public static void addTokenTypes(@NotNull TokenType[] tokens)
    {
        addTokenTypes(tokenTypes, tokens);
    }

    public static void addTokenTypes(@NotNull List<TokenType> list, @NotNull TokenType[] tokens)
    {
        for (TokenType token : tokens)
            addTokenType(list, token);
    }

    // For matchers

    public static void addMatch(@NotNull Matcher match)
    {
//        addMatch(matches, match);
        addMatch(hasReplacements(match) ? replacementMatches : regularMatches, match);
    }

    public static void addMatch(@NotNull List<Matcher> list, @NotNull Matcher match)
    {
        if (!list.contains(match))
            list.add(match);
    }

    public static boolean hasReplacements(@NotNull Matcher match)
    {
        return getReplacements(match).size() < 1;
    }

    @NotNull
    public static List<Class<? extends Matcher>> getReplacements(@NotNull Matcher match)
    {
        MatcherPriority priority = getAnnotation(match);
        if (priority == null)
            return new ArrayList<>();
        return Arrays.asList(priority.replaces());
    }

    // Adding defaults

    @Nullable
    public static MatcherPriority getAnnotation(@NotNull Matcher matcher)
    {
        return matcher.getClass().getAnnotation(MatcherPriority.class);
    }

    public static void addMatches(@NotNull Matcher[] matches)
    {
        addMatches(getMatches(), matches);
    }

    public static void addMatches(@NotNull List<Matcher> list, @NotNull Matcher[] matches)
    {
        for (Matcher match : matches)
            addMatch(list, match);
    }

    @Contract(pure = true)
    public static List<Matcher> getMatches()
    {
        return matches;
    }

    // Getting lists

    public static void prepare()
    {
        addDefaultTokens();
        addDefaultMatches();

        finalizeMatches();
    }

    public static void addDefaultTokens()
    {
        addDefaultTokens(getTokens());
    }

    // LEXING STUFF

    public static void addDefaultMatches()
    {
        addDefaultMatches(getMatches());
    }

    public static void finalizeMatches()
    {
        matches.addAll(regularMatches);

        List<Matcher> removals = new ArrayList<>();
        for (Matcher match : replacementMatches)
        {
            List<Class<? extends Matcher>> replacements = getReplacements(match);

            List<Matcher> replaces = ArrayUtils.multiCheck(matches, replacements, (Matcher m, Class<? extends Matcher> c) -> c.isInstance(m));

            removals.addAll(replaces);
            matches.add(match);
        }

        matches.removeAll(removals);
        matches.sort(MatcherComparator.instance);
    }

    public static void addDefaultTokens(@NotNull List<TokenType> tokens)
    {
        BinaryOperator.addDefaultTypes(tokens);
        Comment.addDefaultTypes(tokens);
        Identifier.addDefaultType(tokens);
        Ignored.addDefaultTypes(tokens);
        Keyword.addDefaultTypes(tokens);
        Literal.addDefaultTypes(tokens);
        OpAssignOperator.addDefaultTypes(tokens);
        Separator.addDefaultTypes(tokens);
        UnaryOperator.addDefaultTypes(tokens);
    }

    @Contract(pure = true)
    public static List<TokenType> getTokens()
    {
        return tokenTypes;
    }

    // Annotation stuff

    public static void addDefaultMatches(@NotNull List<Matcher> match)
    {
        BinaryOperatorMatcher.addDefaultMatches(match);
        CommentMatcher.addDefaultMatches(match);
        IdentifierMatcher.addDefaultMatch(match);
        IgnoredMatcher.addDefaultMatchers(match);
        KeywordMatcher.addDefaultMatches(match);
        LiteralMatcher.addDefaultMatches(match);
        OpAssignOperatorMatcher.addDefaultMatches(match);
        SeparatorMatcher.addDefaultMatches(match);
        UnaryOperatorMatcher.addDefaultMatches(match);
    }

    public static LexReturnData lex(@NotNull LexContext context)
    {
        return lex(context, matches);
    }

    @Nullable
    public static LexReturnData lex(@NotNull LexContext context, @NotNull List<Matcher> matchers)
    {
        Map<Matcher, MatchReturnData> matched = new HashMap<>();

        for (Matcher matcher : matchers)
        {
            MatchReturnData data = matcher.run(context);
            if (data.isMatch())
                matched.put(matcher, data);
        }

        if (matched.size() < 1)
            return null;

        List<Matcher> matchedKeys = new ArrayList<>(matched.keySet());
        matchedKeys.sort(MatcherComparator.instance);
        Matcher         m    = matchedKeys.get(0);
        MatchReturnData data = matched.get(m);

        return new LexReturnData(data.getLength(), m.getType());
    }

    @NotNull
    public static List<Class<? extends Matcher>> getOverrides(@NotNull Matcher match)
    {
        MatcherPriority priority = getAnnotation(match);
        if (priority == null)
            return new ArrayList<>();
        return Arrays.asList(priority.overrides());
    }
}
