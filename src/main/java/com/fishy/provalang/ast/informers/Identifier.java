package com.fishy.provalang.ast.informers;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.nodes.Type;
import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.api.value.builtins.Void;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Identifier extends AstNode implements Type
{

    public String name;

    public Identifier(AstNode parent)
    {
        super(parent.getScope());
    }

    @Override
    public boolean isPrepared()
    {
        return name != null && !name.isEmpty();
    }

    @Override
    public Symbol expr()
    {
        return Void.singleton;
    }

    public Type get()
    {
        return scope.resolve(name).getType();
    }
}
