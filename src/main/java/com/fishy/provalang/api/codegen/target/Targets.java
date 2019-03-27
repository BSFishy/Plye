package com.fishy.provalang.api.codegen.target;

import java.util.ArrayList;
import java.util.List;

public class Targets
{
    public static final Target llvm = new Target("llvm");
    public static final Target java = new Target("java");

    private Targets() {}

    private static final List<Target> targets = new ArrayList<>();

    static void addTarget(Target target)
    {
        targets.add(target);
    }

    public static List<Target> getTargets()
    {
        return targets;
    }
}
