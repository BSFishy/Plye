package com.fishy.provalang.api.lexer.match;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.context.LexContext;
import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.api.lexer.LexTokenInfo;
import com.fishy.provalang.api.lexer.TokenType;
import com.fishy.provalang.api.data.MatchData;
import com.fishy.provalang.api.data.MatchReturnData;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Data
public abstract class Matcher<T extends TokenType> implements Cloneable
{
    private final T           type;

    // Type stuff

    public abstract MatchReturnData run(@NotNull LexContext context);

    public LexToken run(@NotNull LexTokenInfo info, @NotNull String buffer)
    {
        return new LexToken(type, type.cast(info, buffer.toCharArray()));
    }

    // Parent matches

    protected MatchReturnData match(@NotNull LexContext context, @NotNull MatchMethod... methods)
    {
        MatchReturnData data  = new MatchReturnData();
        int             index = 0;

        for (int i = 0; i < methods.length; i++)
        {
            if (!data.isMatch())
                return data;

            MatchMethod method = methods[i];

            MatchData match = method.match(context, index);

            if (!match.isValue())
                return data.setMatch(false);

            index += match.getLookahead();
        }
        data.setMatch(true);
        data.setLength(index);

        return data;
    }

    // Helps

    private char read(@NotNull LexContext context, int index)
    {
        try
        {
            return context.getWrapper().read(index);
        }
        catch (IOException e)
        {
            ProvalangApi.error("Could not read from file(%s): %s", context.getWrapper().reader.filename, e.getMessage());
            return '\u0000';
        }
    }

    // Helper matches

    protected MatchMethod mwhile(MatchMethod method)
    {
        return (LexContext context, int index) -> {
            MatchData data = method.match(context, index);
            if (!data.isValue())
                return new MatchData(false);

            int lookahead = 0;
            while (true)
            {
                data = method.match(context, index + lookahead);
                if (!data.isValue())
                    break;

                lookahead += data.getLookahead();
            }

            return new MatchData(true, lookahead);
        };
    }

    protected MatchMethod muntil(MatchMethod method)
    {
        return (LexContext context, int index) -> {
            int i = 0;
            while (true)
            {
                MatchData data = method.match(context, index + i);

                if (data.isValue())
                    return new MatchData(true, i + data.getLookahead());

                i++;
            }
        };
    }

    protected MatchMethod mand(MatchMethod... methods)
    {
        return (LexContext context, int index) -> {
            int lookahead = 0;

            for (MatchMethod method : methods)
            {
                MatchData data = method.match(context, index);

                if (!data.isValue())
                    return new MatchData(false);

                lookahead = Math.max(lookahead, data.getLookahead());
            }

            return new MatchData(true, lookahead);
        };
    }

    protected MatchMethod mor(MatchMethod... methods)
    {
        return (LexContext context, int index) -> {
            for (MatchMethod method : methods)
            {
                MatchData data = method.match(context, index);

                if (data.isValue())
                    return data;
            }

            return new MatchData(false);
        };
    }

    protected MatchMethod mnot(MatchMethod method)
    {
        return (LexContext context, int index) -> {
            MatchData data = method.match(context, index);
            return new MatchData(!data.isValue(), data.getLookahead());
        };
    }

    protected MatchMethod moptional(MatchMethod method)
    {
        return (LexContext context, int index) -> {
            MatchData data = method.match(context, index);
            return new MatchData(true, data.isValue() ? data.getLookahead() : 0);
        };
    }

    protected MatchMethod mfor(int amount, MatchMethod method)
    {
        return (LexContext context, int index) -> {
            int lookahead = 0;

            for(int i = 0; i < amount; i++)
            {
                MatchData data = method.match(context, index + lookahead);

                if(!data.isValue())
                    return new MatchData(false, lookahead);

                lookahead += data.getLookahead();
            }

            return new MatchData(true, lookahead);
        };
    }

    protected MatchMethod mignoreLookahead(MatchMethod method)
    {
        return (LexContext context, int index) -> {
            MatchData data = method.match(context, index);
            return new MatchData(data.isValue());
        };
    }

    protected MatchMethod mlookbehind(MatchMethod method)
    {
        return (LexContext context, int index) -> {
            if(index == 0) throw new IllegalStateException("Could not look behind index 0");
            return method.match(context, index-1);
        };
    }

    protected MatchMethod m(MatchMethod... methods)
    {
        return (LexContext context, int index) -> {
            int lookahead = 0;

            for (MatchMethod method : methods)
            {
                MatchData data = method.match(context, index + lookahead);

                if (!data.isValue())
                {
                    return new MatchData(false, lookahead);
                }

                lookahead += data.getLookahead();
            }

            return new MatchData(true, lookahead);
        };
    }

    // Matches

    protected MatchMethod m(char c)
    {
        return (LexContext context, int index) -> {
            char ch = read(context, index);
            return new MatchData(ch == c, 1);
        };
    }

    protected MatchMethod mnot(char c)
    {
        return (LexContext context, int index) -> {
            char ch = read(context, index);
            return new MatchData(ch != c, 1);
        };
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
                return new MatchData(ch == c || ((int) ch)-0x20 == (int) c, 1);
            };
        }
        else if (0x61 <= (int) c && 0x7A >= (int) c)
        {
            return (LexContext context, int index) -> {
                char ch = read(context, index);
                return new MatchData(ch == c || ((int) ch)+0x20 == (int) c, 1);
            };
        }
        else
        {
            throw new IllegalArgumentException(String.format("When using anyCase, the character must be a letter: %s", c));
        }
    }

    // Generic helpers

    protected MatchMethod newline()
    {
        return mor(m('\n'), m('\r'));
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
