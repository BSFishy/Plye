package com.fishy.provalang.ast.informers;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.nodes.Type;
import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.api.scope.symbols.informers.VariableNameSymbol;
import com.fishy.provalang.ast.api.value.builtins.Void;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VariableName extends AstNode implements Type
{

    public Type   type;
    public String name;

    // TODO: somehow implement values in here?

    public VariableName(AstNode parent)
    {
        super(parent.getScope());
    }

    @Override
    public boolean isPrepared()
    {
        return type != null && name != null;
    }

    @Override
    public Symbol expr()
    {
        scope.define(new VariableNameSymbol(name, this));

        return Void.singleton;
    }
}
