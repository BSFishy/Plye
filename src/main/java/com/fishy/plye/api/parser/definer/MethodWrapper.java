package com.fishy.plye.api.parser.definer;

import com.fishy.plye.api.context.DefinerContext;
import com.fishy.plye.api.data.parser.definer.DefinitionData;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

@Data
public class MethodWrapper<T, K> implements DefinitionMethod<T, K>
{
    @Nullable
    private final Class<? extends T>     firstType;
    private final DefinitionMethod<T, K> method;
    @Nullable
    private final BiFunction<K, T, K>    function;

    @Contract(pure = true)
    public MethodWrapper(@Nullable Class<? extends T> firstType, DefinitionMethod<T, K> method, @Nullable BiFunction<K, T, K> function) {
        this.firstType = firstType;
        this.method = method;
        this.function = function;
    }

    @Override
    public DefinitionData run(DefinerContext<T, K> context, int index)
    {
        return method.run(context, index);
    }

    @Nullable
    public K perform(K node, T token) {
        if(function != null) {
            return function.apply(node, token);
        }

        return null;
    }
}
