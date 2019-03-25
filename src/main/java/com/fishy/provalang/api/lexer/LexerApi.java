package com.fishy.provalang.api.lexer;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.lexer.data.LexReturnData;
import com.fishy.provalang.api.lexer.data.MatchReturnData;
import com.fishy.provalang.api.lexer.data.util.MatcherComparator;
import com.fishy.provalang.api.lexer.match.Matcher;
import com.fishy.provalang.lexer.matchers.*;
import com.fishy.provalang.lexer.tokens.*;
import com.fishy.provalang.utils.ArrayUtils;
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

    public static void addTokenType(TokenType token)
    {
        addTokenType(tokenTypes, token);
    }

    public static void addTokenType(List<TokenType> list, TokenType token)
    {
        if (!list.contains(token))
            list.add(token);
    }

    public static void addTokenTypes(TokenType[] tokens)
    {
        addTokenTypes(tokenTypes, tokens);
    }

    public static void addTokenTypes(List<TokenType> list, TokenType[] tokens)
    {
        for (TokenType token : tokens)
            addTokenType(list, token);
    }

    // For matchers

    public static void addMatch(Matcher match)
    {
//        addMatch(matches, match);
        addMatch(hasReplacements(match) ? replacementMatches : regularMatches, match);
    }

    public static void addMatch(List<Matcher> list, Matcher match)
    {
        if (!list.contains(match))
            list.add(match);
    }

    public static void addMatches(Matcher[] matches)
    {
        addMatches(getMatches(), matches);
    }

    public static void addMatches(List<Matcher> list, Matcher[] matches)
    {
        for (Matcher match : matches)
            addMatch(list, match);
    }

    // Adding defaults

    public static void addDefaultTokens()
    {
        addDefaultTokens(getTokens());
    }

    public static void addDefaultTokens(List<TokenType> tokens)
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

    public static void addDefaultMatches()
    {
        addDefaultMatches(getMatches());
    }

    public static void addDefaultMatches(List<Matcher> match)
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

    // Getting lists

    public static List<TokenType> getTokens()
    {
        return tokenTypes;
    }

    public static List<Matcher> getMatches()
    {
        return matches;
    }

    // LEXING STUFF

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

    public static LexReturnData lex(LexContext context)
    {
        return lex(context, matches);
    }
//    @Nullable
//    public static LexReturnData lex(FileWrapper file)
//    {
//        return lex(matches, file);
//    }
//
//    @Nullable
//    public static LexReturnData lex(List<Match> matches, FileWrapper file)
//    {
//        return lex(matches.stream()
//                          .map(match -> match.convert(file))
//                          .collect(Collectors.toList()));
//    }

    @Nullable
    public static LexReturnData lex(LexContext context, List<Matcher> matchers)
    {
        Map<Matcher, MatchReturnData> matched = new HashMap<>();

        for(Matcher matcher : matchers)
        {
            MatchReturnData data = matcher.run(context);
            if(data.isMatch())
                matched.put(matcher, data);
        }

        if(matched.size() < 1)
            return null;

        List<Matcher> matchedKeys = new ArrayList<>(matched.keySet());
        matchedKeys.sort(MatcherComparator.instance);
        Matcher m = matchedKeys.get(0);
        MatchReturnData data = matched.get(m);

        return new LexReturnData(data.getLength(), m.getType());
    }

    // Annotation stuff

    @Nullable
    public static MatcherPriority getAnnotation(Matcher matcher)
    {
        return matcher.getClass().getAnnotation(MatcherPriority.class);
    }

    public static boolean hasReplacements(Matcher match)
    {
        return getReplacements(match).size() < 1;
    }

    public static List<Class<? extends Matcher>> getOverrides(Matcher match)
    {
        MatcherPriority priority = getAnnotation(match);
        if (priority == null)
            return new ArrayList<>();
        return Arrays.asList(priority.overrides());
    }

    public static List<Class<? extends Matcher>> getReplacements(Matcher match)
    {
        MatcherPriority priority = getAnnotation(match);
        if (priority == null)
            return new ArrayList<>();
        return Arrays.asList(priority.replaces());
    }
}
