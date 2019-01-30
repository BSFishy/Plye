package com.fishy.provalang.ast.api.scope.symbols;

import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.blocks.container.Class;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ClassSymbol extends Symbol<Class>
{
    public ClassSymbol(String name, Class cl)
    {
        super(name, cl);
    }
}
