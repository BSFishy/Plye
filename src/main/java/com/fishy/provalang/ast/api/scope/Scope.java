package com.fishy.provalang.ast.api.scope;

import org.jetbrains.annotations.Nullable;

public interface Scope
{
    String getScopeName();

    @Nullable Scope getEnclosingScope();

    void define(Symbol symbol);

    Symbol resolve(String name);
}
