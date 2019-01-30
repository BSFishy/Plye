package com.fishy.provalang.ast.api.scope.scopes;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.scope.Scope;
import com.fishy.provalang.ast.api.scope.Symbol;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class GenericScope implements Scope
{
    public       String name;
    @Nullable
    public final Scope  enclosingScope;

    private final Map<String, Symbol> symbols = new HashMap<>();

    public GenericScope(@Nullable AstNode parent)
    {
        if (parent != null) this.enclosingScope = parent.scope;
        else this.enclosingScope = null;
    }

    @Override
    public String getScopeName()
    {
        return name;
    }

    @Override
    @Nullable
    public Scope getEnclosingScope()
    {
        return enclosingScope;
    }

    @Override
    public void define(Symbol symbol)
    {
        symbols.put(symbol.getName(), symbol);
    }

    @Override
    public Symbol resolve(String name)
    {
        Symbol s = symbols.get(name);
        if (s != null)
            return s;

        if (enclosingScope != null)
            return enclosingScope.resolve(name);

        return null;
    }

    @Override
    public String toString()
    {
        return "GenericScope(" + name + ", enclosingScope=" + (enclosingScope != null ? enclosingScope.getScopeName() : "none") + ", symbols=" + symbols.toString() + ")";
    }
}
