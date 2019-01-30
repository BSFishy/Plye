package com.fishy.provalang.ast.informers;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.scope.Symbol;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Package extends AstNode
{

    public Symbol name;

    public Package(AstNode parent)
    {
        super(parent.getScope());
    }

    @Override
    public boolean isPrepared()
    {
        return true;
    }

    @Override
    public Symbol expr()
    {
        return name;
    }
}
