////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.parser.definer;

import com.fishy.plye.api.context.DefinerContext;
import com.fishy.plye.api.data.parser.definer.DefinitionData;
import com.fishy.plye.api.data.parser.definer.DefinitionResult;
import com.fishy.plye.api.matching.AbstractMatcher;
import com.fishy.plye.api.matching.IMethod;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * An object that defines the syntax of a token. The actual process will recieve a list of input tokens, and return either null or the token that this object defines. The
 * process of this class is simply to give the {@link Definition} class the method of matching this token. It then allows the definition to perform the matching, and creation of
 * the token.
 *
 * @param <T> The token that this class defines
 * @param <K> The token that is consumed by this class
 */
@SuppressWarnings({"SpellCheckingInspection", "unused"})
public abstract class Definer<T, K> extends AbstractMatcher<DefinerContext<K, T>, DefinitionMethod<K, T>, DefinitionData>
{
    private Definition<T, K> def;

    @NotNull
    @Contract
    public final Definition<T, K> getDefinition() {
        if(def == null)
            def = define();

        return def;
    }

    @NotNull
    @Override
    public DefinitionMethod<K, T> create(@NotNull IMethod<DefinitionData, DefinerContext<K, T>> method)
    {
        return method::run;
    }

    @NotNull
    @Override
    public DefinitionData create(boolean value, int lookahead)
    {
        return new DefinitionData(value, lookahead);
    }

    @NotNull
    @Override
    public DefinitionData create(boolean value)
    {
        return new DefinitionData(value);
    }

    @NotNull
    @Contract(value = " -> new", pure = true)
    public abstract Definition<T, K> define();

    @NotNull
    @SafeVarargs
    @Contract(value = "_, _ -> new", pure = true)
    public final Definition<T, K> define(@NotNull Supplier<T> create, @NotNull DefinitionMethod<K, T>... methods)
    {
        return new Definition<>(m(methods), create);
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public final MethodWrapper<K, T> m(@NotNull Class<? extends K> token)
    {
        return m(null, token);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public final MethodWrapper<K, T> m(@Nullable BiFunction<T, K, T> function, @NotNull Class<? extends K> token)
    {
        return create((DefinerContext<K, T> context, int index) -> new DefinitionData(token.isInstance(context.get(index)), 1),
                      function);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public final MethodWrapper<K, T> create(@NotNull DefinitionMethod<K, T> method, @Nullable BiFunction<T, K, T> function)
    {
        return new MethodWrapper<>(method, function);
    }

    @SafeVarargs
    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public final MethodWrapper<K, T> m(@NotNull Class<? extends K>... tokens)
    {
        return m(null, tokens);
    }

    @SafeVarargs
    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public final MethodWrapper<K, T> m(@Nullable BiFunction<T, K, T> function, @NotNull Class<? extends K>... tokens)
    {
        return create(m(Arrays.stream(tokens).map(token -> m(function, token)).collect(Collectors.toList())), function);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public final MethodWrapper<K, T> m(@Nullable BiFunction<T, K, T> function, @NotNull DefinitionMethod<K, T> method)
    {
        return create(method, function);
    }

    @NotNull
    @Contract(value = "_, _, _ -> new", pure = true)
    public final MethodWrapper<K, T> m(@Nullable BiFunction<T, T, T> func, @Nullable BiFunction<T, K, T> function, @NotNull Definition<T, K> definition)
    {
        return create((DefinerContext<K, T> context, int index) -> {
            DefinitionResult<T> result = definition.run(context.getTokens(), index);

            if (result.getValue())
            {
                if (func != null)
                {
                    T token = func.apply(context.getCurrentToken(), result.getToken());

                    if (token != null)
                    {
                        context.setCurrentToken(token);
                    }
                }
            }

            return result.getResult();
        }, function);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public final MethodWrapper<K, T> m(@Nullable BiFunction<T, T, T> function, @NotNull Definition<T, K> definition)
    {
        return m(function, null, definition);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public final MethodWrapper<K, T> mwrap(@Nullable BiFunction<T, K, T> function, @NotNull Definition<T, K> definition)
    {
        return m(null, function, definition);
    }


    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public final MethodWrapper<K, T> m(@NotNull Definition<T, K> definition)
    {
        return m(null, null, definition);
    }

    @NotNull
    @Contract(value = "_, _, _ -> new", pure = true)
    public final MethodWrapper<K, T> m(@Nullable BiFunction<T, T, T> func, @Nullable BiFunction<T, K, T> function, @NotNull Definer<T, K> definer)
    {
        return create((DefinerContext<K, T> context, int index) -> m(func, function, definer.getDefinition()).run(context, index), null);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public final MethodWrapper<K, T> m(@Nullable BiFunction<T, T, T> function, @NotNull Definer<T, K> definer)
    {
        return m(function, null, definer);
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public final MethodWrapper<K, T> mwrap(@Nullable BiFunction<T, K, T> function, @NotNull Definer<T, K> definer)
    {
        return m(null, function, definer);
    }


    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public final MethodWrapper<K, T> m(@NotNull Definer<T, K> definer)
    {
        return m(null, null, definer);
    }
}
