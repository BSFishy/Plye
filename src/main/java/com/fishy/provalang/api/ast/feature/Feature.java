package com.fishy.provalang.api.ast.feature;

import com.fishy.provalang.api.ast.AstElement;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public abstract class Feature<T extends FeatureNode> implements AstElement
{
    private final T node;

    public Feature(T node)
    {
        this.node = node;
    }
}
