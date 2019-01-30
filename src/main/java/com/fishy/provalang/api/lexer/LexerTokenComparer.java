package com.fishy.provalang.api.lexer;

import com.fishy.provalang.api.lexer.annotation.OverridableLexerTokenType;
import com.fishy.provalang.api.lexer.annotation.OverridableLexerTokenType.Priority;
import com.fishy.provalang.utils.ArrayUtils;

import java.util.Comparator;

public class LexerTokenComparer implements Comparator<ILexerTokenType>
{
    @Override
    // -1 == less than
    // 0  == equal to
    // 1  == greater than
    public int compare(ILexerTokenType o1, ILexerTokenType o2)
    {
        OverridableLexerTokenType or1 = LexerApi.getOverridable(o1);
        OverridableLexerTokenType or2 = LexerApi.getOverridable(o2);

        if (or1 == null && or2 == null)
            return 0;
        else if (or1 == null)
        {
            if (ArrayUtils.contains(or2.specificOverrides(), o1)) // o1 overrides o2
            {
                return 1;
            }

            return or2.priority().id() - Priority.Normal.id();
        }
        else if (or2 == null)
        {
            if (ArrayUtils.contains(or1.specificOverrides(), o2)) // o2 overrides o1
            {
                return -1;
            }

            return or1.priority().id() - Priority.Normal.id();
        }

        return or1.priority().id() - or2.priority().id();
    }
}
