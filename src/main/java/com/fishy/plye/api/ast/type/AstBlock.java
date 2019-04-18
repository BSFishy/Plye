package com.fishy.plye.api.ast.type;

import com.fishy.plye.api.ast.AstNode;
import com.fishy.plye.api.ast.feature.FeatureNode;

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
