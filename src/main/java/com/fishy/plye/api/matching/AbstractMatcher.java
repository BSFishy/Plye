////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.matching;

import com.fishy.plye.api.data.AbstractMatchingData;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public abstract class AbstractMatcher<C, M extends IMethod<D, C>, D extends AbstractMatchingData>
{
    @NotNull
    @SafeVarargs
    @Contract(value = "_ -> new", pure = true)
    protected final M m(M... methods)
    {
        return m(Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M m(Collection<M> methods)
    {
        return create((C context, int index) -> {
            int lookahead = 0;

            for (M method : methods)
            {
                D data = method.run(context, index + lookahead);

                if (!data.isValue())
                    return create(false, lookahead);

                lookahead += data.getLookahead();
            }

            return create(true, lookahead);
        });
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected abstract M create(@NotNull IMethod<D, C> method);

    // Helper methods

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    protected abstract D create(boolean value, int lookahead);

    @NotNull
    @SafeVarargs
    @Contract(value = "_ -> new", pure = true)
    protected final M mwhile(M... methods)
    {
        return mwhile(Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mwhile(Collection<M> methods)
    {
        return mwhile(m(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mwhile(M method)
    {
        return create((C context, int index) -> {
            D data = method.run(context, index);
            if (!data.isValue())
                return create(false);

            int lookahead = data.getLookahead();
            while (true)
            {
                data = method.run(context, index + lookahead);
                if (!data.isValue())
                    break;

                lookahead += data.getLookahead();
            }

            return create(true, lookahead);
        });
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected abstract D create(boolean value);

    @NotNull
    @SafeVarargs
    @Contract(value = "_ -> new", pure = true)
    protected final M muntil(M... methods)
    {
        return muntil(Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M muntil(Collection<M> methods)
    {
        return muntil(m(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M muntil(M method)
    {
        return create((C context, int index) -> {
            int lookahead = 0;
            while (true)
            {
                D data = method.run(context, index);

                lookahead += Math.max(data.getLookahead(), 1);
                if (data.isValue())
                    return create(true, lookahead);
            }
        });
    }

    @NotNull
    @SafeVarargs
    @Contract(value = "_ -> new", pure = true)
    protected final M mand(M... methods)
    {
        return mand(Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mand(Collection<M> methods)
    {
        return create((C context, int index) -> {
            int lookahead = 0;

            for (M method : methods)
            {
                D data = method.run(context, index);

                if (!data.isValue())
                    return create(false);

                lookahead = Math.max(lookahead, data.getLookahead());
            }

            return create(true, lookahead);
        });
    }

    @NotNull
    @SafeVarargs
    @Contract(value = "_ -> new", pure = true)
    protected final M mor(M... methods)
    {
        return mor(Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mor(Collection<M> methods)
    {
        return create((C context, int index) -> {
            for (M method : methods)
            {
                D data = method.run(context, index);

                if (data.isValue())
                    return data;
            }

            return create(false);
        });
    }

    @NotNull
    @SafeVarargs
    @Contract(value = "_ -> new", pure = true)
    protected final M mnot(M... methods)
    {
        return mnot(Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mnot(Collection<M> methods)
    {
        return mnot(m(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mnot(M method)
    {
        return create((C context, int index) -> {
            D data = method.run(context, index);
            return create(!data.isValue(), data.getLookahead());
        });
    }

    @NotNull
    @SafeVarargs
    @Contract(value = "_ -> new", pure = true)
    protected final M moptional(M... methods)
    {
        return moptional(Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M moptional(Collection<M> methods)
    {
        return moptional(m(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M moptional(M method)
    {
        return create((C context, int index) -> {
            D data = method.run(context, index);
            return create(true, data.isValue() ? data.getLookahead() : 0);
        });
    }

    @NotNull
    @SafeVarargs
    @Contract(value = "_, _ -> new", pure = true)
    protected final M mfor(int amount, M... methods)
    {
        return mfor(amount, Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    protected final M mfor(int amount, Collection<M> methods)
    {
        return mfor(amount, m(methods));
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    protected final M mfor(int amount, M method)
    {
        return create((C context, int index) -> {
            int lookahead = 0;

            for (int i = 0; i < amount; i++)
            {
                D data = method.run(context, index);

                if (!data.isValue())
                    return create(false, lookahead);

                lookahead += data.getLookahead();
            }

            return create(true, lookahead);
        });
    }

    @NotNull
    @SafeVarargs
    @Contract(value = "_ -> new", pure = true)
    protected final M mignoreLookahead(M... methods)
    {
        return mignoreLookahead(Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mignoreLookahead(Collection<M> methods)
    {
        return mignoreLookahead(m(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mignoreLookahead(M method)
    {
        return create((C context, int index) -> {
            D data = method.run(context, index);
            return create(data.isValue());
        });
    }

    @NotNull
    @SafeVarargs
    @Contract(value = "_, _ -> new", pure = true)
    protected final M mlookbehind(int amount, M... methods)
    {
        return mlookbehind(amount, Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    protected final M mlookbehind(int amount, Collection<M> methods)
    {
        return mlookbehind(amount, m(methods));
    }

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    protected final M mlookbehind(int amount, M method)
    {
        return create((C context, int index) -> {
            if (index - amount < 0) throw new IllegalStateException(String.format("Could not look behind index %d", index));
            return method.run(context, index - amount);
        });
    }

    @NotNull
    @SafeVarargs
    @Contract(value = "_ -> new", pure = true)
    protected final M mlookbehind(M... methods)
    {
        return mlookbehind(Arrays.asList(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mlookbehind(Collection<M> methods)
    {
        return mlookbehind(m(methods));
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    protected final M mlookbehind(M method)
    {
        return mlookbehind(1, method);
    }
}
