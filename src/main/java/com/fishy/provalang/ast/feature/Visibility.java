package com.fishy.provalang.ast.feature;

import com.fishy.provalang.api.ast.feature.Feature;
import com.fishy.provalang.api.ast.type.ContainerNode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Visibility extends Feature<ContainerNode>
{
    public final Values value;

    public Visibility(ContainerNode node, Values value)
    {
        super(node);

        this.value = value;
    }

    public enum Values {
        Public,
        Protected,
        PackagePrivate,
        Private
    }
}
