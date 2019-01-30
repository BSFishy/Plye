package com.fishy.provalang.ast.api.nodes;

import com.fishy.provalang.ast.api.scope.Symbol;

import java.util.List;

public interface ICallableBlock
{
    Symbol run(List<Symbol> arguments);
}
