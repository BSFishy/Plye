package com.fishy.provalang.ast.api;

import com.fishy.provalang.ast.api.scope.Scope;
import com.fishy.provalang.ast.api.scope.Symbol;
import lombok.Data;

@Data
public abstract class AstNode
{
    public final Scope scope;

    public abstract boolean isPrepared();

    public abstract Symbol expr();
}
