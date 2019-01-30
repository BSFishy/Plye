package com.fishy.provalang.ast.api.scope;

import com.fishy.provalang.ast.api.nodes.Type;
import lombok.Data;

@Data
public class Symbol<T extends Type>
{
    public final String name;
    public final T      type;
}
