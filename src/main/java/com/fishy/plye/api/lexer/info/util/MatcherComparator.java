////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.lexer.info.util;

import com.fishy.plye.api.annotations.MatcherPriority;
import com.fishy.plye.api.annotations.Priority;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.match.Matcher;
import com.fishy.plye.utils.ArrayUtils;

import java.util.Comparator;

public class MatcherComparator implements Comparator<Matcher>
{
    public static final MatcherComparator instance = new MatcherComparator();

    @Override
    @SuppressWarnings("FeatureEnvy")
    // -1 == less than, 1 should be before 2
    // 0  == equal to, the same
    // 1  == greater than, 1 should be after 2
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
