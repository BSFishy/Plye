////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.parser.definer;

import com.fishy.plye.api.PlyeApi;
import com.fishy.plye.api.context.DefinerContext;
import com.fishy.plye.api.data.parser.definer.DefinitionData;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

@Data
public class MethodWrapper<T, K> implements DefinitionMethod<T, K>
{
    private final DefinitionMethod<T, K> method;
    @Nullable
    private final BiFunction<K, T, K>    function;

    @Contract(pure = true)
    public MethodWrapper(DefinitionMethod<T, K> method, @Nullable BiFunction<K, T, K> function)
    {
        this.method = method;
        this.function = function;
    }

    @Override
    public DefinitionData run(DefinerContext<T, K> context, int index)
    {
        DefinitionData data = method.run(context, index);

        if (data.isValue())
        {
            for (int i = 0; i < data.getLookahead(); i++)
            {
                K token = perform(context.getCurrentToken(), context.get(index + i));

                if (token != null)
                {
                    context.setCurrentToken(token);
                }
            }
        }

        return data;
    }

    @Nullable
    public K perform(K node, T token)
    {
        if (function != null)
        {
            return function.apply(node, token);
        }

        return null;
    }
}
