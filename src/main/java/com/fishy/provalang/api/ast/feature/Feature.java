package com.fishy.provalang.api.ast.feature;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public abstract class Feature<T extends FeatureNode>
{
    private final T node;

    public Feature(T node)
    {
        this.node = node;
    }
}
