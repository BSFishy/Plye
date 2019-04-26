package com.fishy.plye.api.lexer.match;

import com.fishy.plye.api.PlyeApi;
import com.fishy.plye.api.context.LexContext;
import com.fishy.plye.api.data.lexer.MatchData;
import com.fishy.plye.api.data.lexer.MatchReturnData;
import com.fishy.plye.api.lexer.LexToken;
import com.fishy.plye.api.lexer.LexTokenInfo;
import com.fishy.plye.api.lexer.TokenType;
import com.fishy.plye.api.matching.AbstractMatcher;
import com.fishy.plye.api.matching.IMethod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class Matcher<T extends TokenType> extends AbstractMatcher<LexContext, MatchMethod, MatchData>
{
    private final T type;

    // Type stuff

    public abstract MatchReturnData run(LexContext context);

    public LexToken run(@NotNull LexTokenInfo info, @NotNull String buffer)
    {
        return new LexToken(type, type.cast(info, buffer.toCharArray()));
    }

    @NotNull
    @Override
    protected MatchMethod create(@NotNull IMethod<MatchData, LexContext> method)
    {
        return method::run;
//        return (MatchMethod) method;
    }

    @NotNull
    @Override
    protected MatchData create(boolean value, int lookahead)
    {
        return new MatchData(value, lookahead);
    }

    @NotNull
    @Override
    protected MatchData create(boolean value)
    {
        return new MatchData(value);
    }

    // Parent matches

    @SuppressWarnings("FeatureEnvy")
    protected MatchReturnData match(@NotNull LexContext context, @NotNull MatchMethod... methods)
    {
        MatchReturnData data  = new MatchReturnData();
        int             index = 0;

        for (MatchMethod method : methods)
        {
            if (!data.isMatch())
                return data;

            MatchData match = method.run(context, index);

            if (!match.isValue())
                return data.setMatch(false);

            index += match.getLookahead();
        }
        data.setMatch(true);
        data.setLength(index);

        return data;
    }

    // Helps

    protected MatchMethod mnot(char c)
    {
        return (LexContext context, int index) -> {
            char ch = read(context, index);
            return new MatchData(ch != c, 1);
        };
    }

    // Matches

    private char read(@NotNull LexContext context, int index)
    {
        try
        {
            return context.getWrapper().read(index);
        }
        catch (IOException e)
        {
            PlyeApi.error("Could not read from file(%s): %s", context.getWrapper().reader.filename, e.getMessage());
            return '\u0000';
        }
    }

    protected MatchMethod m(String s)
    {
        return (LexContext context, int index) -> {
            for (int i = 0; i < s.length(); i++)
            {
                char c  = read(context, index + i);
                char sc = s.charAt(i);

                if (c != sc)
                {
                    return new MatchData(false, i);
                }
            }

            return new MatchData(true, s.length());
        };
    }

    protected MatchMethod minRange(int lo, int hi)
    {
        return (LexContext context, int index) -> {
            char c = read(context, index);
            return new MatchData((int) c >= lo && (int) c <= hi, 1);
        };
    }

    protected MatchMethod manyCase(char c)
    {
        if (0x41 <= (int) c && 0x5A >= (int) c)
        {
            return (LexContext context, int index) -> {
                char ch = read(context, index);
                return new MatchData(ch == c || ((int) ch) - 0x20 == (int) c, 1);
            };
        }
        else if (0x61 <= (int) c && 0x7A >= (int) c)
        {
            return (LexContext context, int index) -> {
                char ch = read(context, index);
                return new MatchData(ch == c || ((int) ch) + 0x20 == (int) c, 1);
            };
        }
        else
        {
            throw new IllegalArgumentException(String.format("When using anyCase, the character must be a letter: %s", c));
        }
    }

    protected MatchMethod newline()
    {
        return mor(m('\n'), m('\r'));
    }

    // Generic helpers

    protected MatchMethod m(char c)
    {
        return (LexContext context, int index) -> {
            char ch = read(context, index);
            return new MatchData(ch == c, 1);
        };
    }

    protected MatchMethod uppercase()
    {
        return minRange(0x41, 0x5A);
    }

    protected MatchMethod lowercase()
    {
        return minRange(0x61, 0x7A);
    }

    protected MatchMethod number()
    {
        return minRange(0x30, 0x39);
    }

    protected MatchMethod identifierChar()
    {
        // A-Z, a-z, 0-9, _
        return mor(uppercase(), lowercase(), number(), m('_'));
    }

    protected MatchMethod identifier()
    {
        return mwhile(identifierChar());
    }
}
