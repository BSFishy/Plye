package com.fishy.provalang.ast.api.nodes;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.scope.Scope;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AstBlock extends AstNode
{
    public List<AstNode> children = new ArrayList<>();

    public AstBlock(Scope scope)
    {
        super(scope);
    }

    @Override
    public boolean isPrepared()
    {
        return true;
    }

    public void addChildren(AstNode node)
    {
        if (!children.contains(node))
            children.add(node);
    }
}