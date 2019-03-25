package com.fishy.provalang.api.lexer.match;

import com.fishy.provalang.api.file.FileWrapper;
import com.fishy.provalang.api.lexer.TokenType;
import lombok.Data;

@Data
public class Match<T extends Matcher<? extends TokenType>>
{
    private final T matcher;

    public static <T extends Matcher<? extends TokenType>> Match<T> of(T matcher)
    {
        return new Match<T>(matcher);
    }

    public T convert(FileWrapper wrapper)
    {
        return (T) matcher.copy(wrapper);
    }
}
