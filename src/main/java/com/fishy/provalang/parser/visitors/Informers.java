package com.fishy.provalang.parser.visitors;

import com.fishy.provalang.api.parser.ParserApi;
import com.fishy.provalang.api.parser.visitor.Visitor;
import com.fishy.provalang.parser.visitors.informers.ImportVisitor;
import com.fishy.provalang.parser.visitors.informers.PackageVisitor;

import java.util.List;

public class Informers
{
    public static final PackageVisitor packageVisitor = new PackageVisitor();
    public static final ImportVisitor importVisitor = new ImportVisitor();

    public static void addDefaultTypes()
    {
        addDefaultTypes(ParserApi.getVisitors());
    }

    public static void addDefaultTypes(List<Visitor> visitors)
    {
        ParserApi.addVisitors(visitors, packageVisitor, importVisitor);
    }
}
