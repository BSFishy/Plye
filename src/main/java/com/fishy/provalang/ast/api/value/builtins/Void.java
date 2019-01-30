package com.fishy.provalang.ast.api.value.builtins;

import com.fishy.provalang.ast.api.nodes.Type;
import com.fishy.provalang.ast.api.scope.Symbol;

public class Void extends Symbol<Void.VoidType>
{

    public static final VoidType voidType = new VoidType();
    public static final Void singleton = new Void();

    public Void()
    {
        super(voidType.getName(), voidType);
    }

    public static class VoidType implements Type{

        @Override
        public String getName()
        {
            return "null";
        }
    }
}
