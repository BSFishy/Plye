package com.fishy.provalang.ast.api.scope.symbols;

import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.blocks.container.Method;

public class MethodSymbol extends Symbol<Method>
{
    public MethodSymbol(String name, Method type)
    {
        super(name, type);
    }
}
