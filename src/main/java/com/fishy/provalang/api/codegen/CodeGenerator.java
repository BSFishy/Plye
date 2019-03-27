package com.fishy.provalang.api.codegen;

import com.fishy.provalang.api.codegen.target.Target;
import com.fishy.provalang.api.context.CodegenContext;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public abstract class CodeGenerator
{
    @EqualsAndHashCode.Include private final Target target;

    public CodeGenerator(Target target)
    {
        this.target = target;
    }

    public abstract void codegen(CodegenContext context);
}
