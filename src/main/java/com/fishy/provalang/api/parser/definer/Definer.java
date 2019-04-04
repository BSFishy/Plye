package com.fishy.provalang.api.parser.definer;

import com.fishy.provalang.api.context.DefinerContext;
import com.fishy.provalang.api.data.parser.DefinitionData;
import com.fishy.provalang.api.matching.AbstractMatcher;
import com.fishy.provalang.api.matching.IMethod;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public abstract class Definer<T, K> extends AbstractMatcher<DefinerContext, DefinitionMethod, DefinitionData>
{
    public abstract Definition define();

    @Override
    protected DefinitionData create(boolean value) {
        return new DefinitionData(value);
    }

    @Override
    protected DefinitionData create(boolean value, int lookahead) {
        return new DefinitionData(value, lookahead);
    }

    @Override
    protected DefinitionMethod create(@NotNull IMethod<DefinitionData, DefinerContext> method) {
        return method::run;
    }

    // Methods to assist the defining

    protected Definition define(DefinitionMethod... methods)
    {
        Definition output = new Definition();
        output.addAllMethods(Arrays.asList(methods));
        return output;
    }

    // matching
}
