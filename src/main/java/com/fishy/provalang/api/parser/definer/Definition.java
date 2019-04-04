package com.fishy.provalang.api.parser.definer;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class Definition
{
    public final List<DefinitionMethod> methods = new ArrayList<>();

    public void addMethod(DefinitionMethod method)
    {
        methods.add(method);
    }

    public void addAllMethods(@NotNull Collection<? extends DefinitionMethod> methods) {
        this.methods.addAll(methods);
    }

    public void run() {

    }
}
