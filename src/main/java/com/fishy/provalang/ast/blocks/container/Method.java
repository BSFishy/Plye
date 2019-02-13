package com.fishy.provalang.ast.blocks.container;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.nodes.AstBlock;
import com.fishy.provalang.ast.api.nodes.AstContainer;
import com.fishy.provalang.ast.api.nodes.ICallableBlock;
import com.fishy.provalang.ast.api.nodes.Type;
import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.api.scope.scopes.ClassScope;
import com.fishy.provalang.ast.api.scope.scopes.MethodScope;
import com.fishy.provalang.ast.api.scope.symbols.blocks.MethodSymbol;
import com.fishy.provalang.ast.api.value.builtins.Void;
import com.fishy.provalang.ast.informers.VariableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Method extends AstBlock implements Type, ICallableBlock
{

    public String name;

    public final List<VariableName> parameters = new ArrayList<>();

    public Type returnType;

    public Method(AstContainer parent)
    {
        super(new MethodScope(parent));
    }

    @Override
    public boolean isPrepared()
    {
        return super.isPrepared() && !name.isEmpty() && returnType != null;
    }

    @Override
    public Symbol expr()
    {
        for(VariableName var : parameters)
            var.expr(); // TODO: compensate for returning

        return Void.singleton;
    }

    @Override
    public Symbol run(List<Symbol> arguments)
    {
        for(Symbol s : arguments)
            ((MethodScope) scope).defineTemporary(s); // TODO: test arguments vs parameters

        for(AstNode node : children)
        {
            node.expr();
        }

        ((MethodScope) scope).clearTemporaryVariables();

        if(!(returnType instanceof Void.VoidType))
            ProvalangApi.error("Method %s expected return type %s, but got nothing", name, returnType.getName());

        return Void.singleton;
    }

    public void setName(String name)
    {
        this.name = name;
        ((ClassScope) scope).name = name; // set this scope's name

        if (scope.getEnclosingScope() != null)
            scope.getEnclosingScope().define(new MethodSymbol(name, this)); // add this symbol to the parent scope
    }
}
