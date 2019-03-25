package com.fishy.provalang.api.lexerNew.data.util;

import com.fishy.provalang.api.annotations.MatcherPriority;
import com.fishy.provalang.api.annotations.MatcherPriority.Priority;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.match.Matcher;
import com.fishy.provalang.utils.ArrayUtils;

import java.util.Comparator;

public class MatcherComparator implements Comparator<Matcher>
{
    public static final MatcherComparator instance = new MatcherComparator();

    @Override
    // -1 == less than
    // 0  == equal to
    // 1  == greater than
    public int compare(Matcher o1, Matcher o2)
    {
        MatcherPriority m1 = LexerApi.getAnnotation(o1);
        MatcherPriority m2 = LexerApi.getAnnotation(o2);

        int compare;
        if (m1 == null && m2 == null)
            return 0;
        else if (m2 == null)
        {
            if (ArrayUtils.contains(m1.overrides(), o2))
                return -1;

            compare = m1.priority().id() - Priority.Normal.id();
        }
        else if (m1 == null)
        {
            if (ArrayUtils.contains(m2.overrides(), o1))
                return 1;

            compare = m2.priority().id() - Priority.Normal.id();
        }
        else
        {
            compare = m2.priority().id() - m1.priority().id();
        }

        return Integer.compare(compare, 0);
    }
}
