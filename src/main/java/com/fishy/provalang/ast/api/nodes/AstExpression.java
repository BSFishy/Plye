package com.fishy.provalang.ast.api.nodes;

import com.fishy.provalang.ast.api.AstNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AstExpression extends AstNode
{
    public AstExpression()
    {
        super(null);
    }

    @Override
    public boolean isPrepared()
    {
        return true;
    }
}