package com.fishy.provalang.api.parser;

import com.fishy.provalang.api.ast.AstNode;
import com.fishy.provalang.api.context.ParseContext;
import com.fishy.provalang.api.data.NodeHandlerData;

public abstract class NodeHandler<T extends AstNode>
{
    public abstract NodeHandlerData quickCheck(ParseContext context);

    public abstract T handle(ParseContext context);
}
