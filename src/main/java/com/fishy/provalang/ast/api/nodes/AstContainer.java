package com.fishy.provalang.ast.api.nodes;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.scope.Scope;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AstContainer extends AstNode
{
    public List<AstBlock> children = new ArrayList<>();

    public AstContainer(Scope scope)
    {
        super(scope);
    }

    @Override
    public boolean isPrepared()
    {
        return true;
    }

    public void addChild(AstBlock child)
    {
        if(!children.contains(child))
            children.add(child);
    }
}
