////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.ast.feature;

import com.fishy.plye.api.ast.AstElement;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.Contract;

@EqualsAndHashCode
@ToString
public abstract class Feature<T extends FeatureNode> implements AstElement
{
    private final T node;

    @Contract(pure = true)
    public Feature(T node)
    {
        this.node = node;
    }
}
