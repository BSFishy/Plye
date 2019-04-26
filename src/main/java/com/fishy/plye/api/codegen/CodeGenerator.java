package com.fishy.plye.api.codegen;

import com.fishy.plye.api.codegen.target.Target;
import com.fishy.plye.api.context.CodegenContext;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.jetbrains.annotations.Contract;

@EqualsAndHashCode
@ToString
public abstract class CodeGenerator
{
    @EqualsAndHashCode.Include
    private final Target target;

    @Contract(pure = true)
    public CodeGenerator(Target target)
    {
        this.target = target;
    }

    public abstract void codegen(CodegenContext context);
}
