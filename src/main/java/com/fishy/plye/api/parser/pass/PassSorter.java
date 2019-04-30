////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.parser.pass;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Comparator;
import java.util.List;

public class PassSorter implements Comparator<ParserPass>
{
    public static final PassSorter instance = new PassSorter();

    public static void sort(@NotNull List<? extends ParserPass> list)
    {
        list.sort(instance);
    }

    @Override
    // -1 == less than, 1 should be before 2
    // 0  == equal to, the same
    // 1  == greater than, 1 should be after 2
    public int compare(@NotNull ParserPass o1, @NotNull ParserPass o2)
    {
        @Nullable Class c1 = o1.previousPass();
        @Nullable Class c2 = o2.previousPass();

        if (c1 == null && c2 != null)
        {
            return -1;
        }
        else if (c2 == null && c1 != null)
        {
            return 1;
        }
        else if (c1 == null)
        {
            return 0;
        }

        if (o1.getClass().isAssignableFrom(c2))
        {
            return -1;
        }
        else if (o2.getClass().isAssignableFrom(c1))
        {
            return 1;
        }

        return 0;
    }
}
