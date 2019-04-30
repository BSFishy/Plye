////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.ast.feature;

import com.fishy.plye.api.ast.feature.Feature;
import com.fishy.plye.api.ast.feature.FeatureNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Name extends Feature<FeatureNode>
{
    public final String name;

    public Name(FeatureNode node, String name)
    {
        super(node);
        this.name = name;
    }
}
