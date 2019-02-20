package com.fishy.provalang.api.lexerNew.match;

import com.fishy.provalang.api.lexerNew.TokenType;
import com.fishy.provalang.api.lexerNew.data.MatchData;
import com.fishy.provalang.api.lexerNew.data.PersistentMatchData;
import lombok.Data;

@Data
public class Match<T extends TokenType>
{
    public final Matcher<T> matcher;
    public final PersistentMatchData data;

    public static <T extends TokenType> Match<T> of(Matcher<T> m)
    {
        return new Match<>(m, new PersistentMatchData(0));
    }

    public MatchData run(char currentCharacter, int position)
    {
        return matcher.run(data, currentCharacter, position);
    }
}
