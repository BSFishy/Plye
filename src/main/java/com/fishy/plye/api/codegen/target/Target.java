package com.fishy.plye.api.codegen.target;

import lombok.Data;

@Data
public class Target
{
    public final String name;

    public Target(String name)
    {
        this.name = name;

        Targets.addTarget(this);
    }
}
