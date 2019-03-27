package com.fishy.provalang.api.ast.visitor;

import com.fishy.provalang.api.context.ParseContext;

public interface IVisitor<T>
{
    T visit(ParseContext context);

    void clean(ParseContext context);
}
