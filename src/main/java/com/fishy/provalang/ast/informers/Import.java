package com.fishy.provalang.ast.informers;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.scope.Symbol;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Import extends AstNode
{

    public Symbol name;

    public Import(AstNode parent)
    {
        super(parent.getScope());
    }

    @Override
    public boolean isPrepared()
    {
        return name != null;
    }

    @Override
    public Symbol expr()
    {
        return name;
    }
}
