package com.fishy.provalang.api.ast.type;

import com.fishy.provalang.api.ast.AstNode;
import com.fishy.provalang.api.ast.feature.FeatureNode;

import java.util.ArrayList;
import java.util.List;

public abstract class AstBlock extends FeatureNode
{
    protected final List<AstNode> children = new ArrayList<>();

    public void addChild(AstNode child) {
        children.add(child);
    }

    public List<AstNode> getChildren()
    {
        return children;
    }
}
