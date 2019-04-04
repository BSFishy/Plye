package com.fishy.provalang.ast.feature;

import com.fishy.provalang.api.ast.feature.Feature;
import com.fishy.provalang.api.ast.feature.FeatureNode;
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
