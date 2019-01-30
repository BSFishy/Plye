package com.fishy.provalang.ast.api.scope.scopes;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.scope.Symbol;

import java.util.HashMap;
import java.util.Map;

public class MethodScope extends GenericScope
{

    private final Map<String, Symbol> tempSymbols = new HashMap<>();

    public MethodScope(AstNode parent)
    {
        super(parent);
    }

    public void defineTemporary(Symbol symbol)
    {
        tempSymbols.put(symbol.getName(), symbol);
    }

    public void clearTemporaryVariables()
    {
        tempSymbols.clear();
    }

    @Override
    public Symbol resolve(String name)
    {
        Symbol s = tempSymbols.get(name);

        if(s != null) return s;
        return super.resolve(name);
    }
}
