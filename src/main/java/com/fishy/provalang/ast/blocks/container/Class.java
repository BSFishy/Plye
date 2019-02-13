package com.fishy.provalang.ast.blocks.container;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.nodes.AstContainer;
import com.fishy.provalang.ast.api.nodes.Type;
import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.api.scope.scopes.ClassScope;
import com.fishy.provalang.ast.api.scope.symbols.blocks.ClassSymbol;
import com.fishy.provalang.ast.api.value.builtins.Void;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Class extends AstContainer implements Type
{

    public String name;

    public Class(AstNode parent)
    {
        super(new ClassScope(parent));
    }

    @Override
    public boolean isPrepared()
    {
        return super.isPrepared() && !name.isEmpty();
    }

    @Override
    public Symbol expr()
    {
        return Void.singleton;
    }

    public void setName(String name)
    {
        this.name = name;
        ((ClassScope) scope).name = name; // set this scope's name

        if (scope.getEnclosingScope() != null)
            scope.getEnclosingScope().define(new ClassSymbol(name, this)); // add this symbol to the parent scope
    }
}
