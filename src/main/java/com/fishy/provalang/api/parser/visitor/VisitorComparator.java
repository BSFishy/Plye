package com.fishy.provalang.api.parser.visitor;

import com.fishy.provalang.api.parser.ParserApi;
import com.fishy.provalang.api.parser.annotation.OverridableVisitor;
import com.fishy.provalang.utils.ArrayUtils;

import java.util.Comparator;

public class VisitorComparator implements Comparator<Visitor>
{
    public static final VisitorComparator singleton = new VisitorComparator();

    @Override
    public int compare(Visitor o1, Visitor o2)
    {
        OverridableVisitor or1 = ParserApi.getOverridable(o1);
        OverridableVisitor or2 = ParserApi.getOverridable(o2);

        if (or1 == null && or2 == null)
            return 0;
        else if (or1 == null)
        {
            if (ArrayUtils.contains(or2.specificOverrides(), o1)) // o1 overrides o2
            {
                return 1;
            }

            return or2.priority().id() - OverridableVisitor.Priority.Normal.id();
        }
        else if (or2 == null)
        {
            if (ArrayUtils.contains(or1.specificOverrides(), o2)) // o2 overrides o1
            {
                return -1;
            }

            return or1.priority().id() - OverridableVisitor.Priority.Normal.id();
        }

        return or1.priority().id() - or2.priority().id();
    }
}
