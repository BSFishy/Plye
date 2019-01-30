package com.fishy.provalang.ast.api.scope.symbols;

import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.informers.VariableName;

public class VariableNameSymbol extends Symbol<VariableName>
{
    public VariableNameSymbol(String name, VariableName type)
    {
        super(name, type);
    }
}
