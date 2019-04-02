package com.fishy.provalang.api.codegen.target;

import org.jetbrains.annotations.Contract;

import java.util.ArrayList;
import java.util.List;

public class Targets
{
    public static final Target llvm = new Target("llvm");
    public static final Target java = new Target("java");

    @Contract(pure = true)
    private Targets() {}

    private static final List<Target> targets = new ArrayList<>();

    static void addTarget(Target target)
    {
        targets.add(target);
    }

    @Contract(pure = true)
    public static List<Target> getTargets()
    {
        return targets;
    }
}
