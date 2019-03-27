package com.fishy.provalang.api.parser;

import com.fishy.provalang.api.parser.annotation.OverridableVisitor;
import com.fishy.provalang.api.parser.visitor.Visitor;
import com.fishy.provalang.parser.visitors.Informers;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ParserApi
{
    public static final List<Visitor> visitors = new ArrayList<>();

    // LIST STUFF

    public static List<Visitor> getVisitors()
    {
        return visitors;
    }

    public static void addVisitor(Visitor v)
    {
        addVisitor(visitors, v);
    }

    public static void addVisitor(List<Visitor> list, Visitor v)
    {
        if (!list.contains(v))
            list.add(v);
    }

    public static void addVisitors(Visitor... vs)
    {
        addVisitors(visitors, vs);
    }

    public static void addVisitors(List<Visitor> list, Visitor... vs)
    {
        for(Visitor v : vs)
            addVisitor(list, v);
    }

    public static void addDefaultVisitors()
    {
        addDefaultVisitors(visitors);
    }

    public static void addDefaultVisitors(List<Visitor> list)
    {
        Informers.addDefaultTypes(list);
    }

    // VISITORS

    // TODO: make visit methods

    // ANNOTATION

    @Nullable
    public static OverridableVisitor getOverridable(Visitor v)
    {
        Class<? extends Visitor> clazz = v.getClass();
        return clazz.isAnnotationPresent(OverridableVisitor.class) ? clazz.getAnnotation(OverridableVisitor.class) : null;
    }
}
