package com.fishy.provalang.api.matching;

import com.fishy.provalang.api.data.AbstractMatchingData;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"unused", "SpellCheckingInspection"})
public abstract class AbstractMatcher<C, M extends IMethod<D, C>, D extends AbstractMatchingData>
{
    protected abstract D create(boolean value);
    protected abstract D create(boolean value, int lookahead);

    protected abstract M create(@NotNull IMethod<D, C> method);

    // Helper methods

    @SafeVarargs
    protected final M m(M... methods) {
        return create((C context, int index) -> {
            int lookahead = 0;

            for(M method : methods) {
                D data = method.run(context, index + lookahead);

                if(!data.isValue())
                    return create(false, lookahead);

                lookahead += data.getLookahead();
            }

            return create(true, lookahead);
        });
    }

    protected M mwhile(M method) {
        return create((C context, int index) -> {
            D data = method.run(context, index);
            if(!data.isValue())
                return create(false);

            int lookahead = data.getLookahead();
            while(true) {
                data = method.run(context, index + lookahead);
                if(!data.isValue())
                    break;

                lookahead += data.getLookahead();
            }

            return create(true, lookahead);
        });
    }

    @SafeVarargs
    protected final M mwhile(M... methods) {
        return mwhile(m(methods));
    }

    protected M muntil(M method) {
        return create((C context, int index) -> {
            int lookahead = 0;
            while (true) {
                D data = method.run(context, index);

                lookahead += Math.max(data.getLookahead(), 1);
                if(data.isValue())
                    return create(true, lookahead);
            }
        });
    }

    @SafeVarargs
    protected final M muntil(M... methods) {
        return muntil(m(methods));
    }

    @SafeVarargs
    protected final M mand(M... methods) {
        return create((C context, int index) -> {
            int lookahead = 0;

            for(M method : methods) {
                D data = method.run(context, index);

                if(!data.isValue())
                    return create(false);

                lookahead = Math.max(lookahead, data.getLookahead());
            }

            return create(true, lookahead);
        });
    }

    @SafeVarargs
    protected final M mor(M... methods) {
        return create((C context, int index) -> {
            for(M method : methods) {
                D data = method.run(context, index);

                if(data.isValue())
                    return data;
            }

            return create(false);
        });
    }

    protected M mnot(M method) {
        return create((C context, int index) -> {
            D data = method.run(context, index);
            return create(!data.isValue(), data.getLookahead());
        });
    }

    @SafeVarargs
    protected final M mnot(M... methods) {
        return mnot(m(methods));
    }

    protected M moptional(M method) {
        return create((C context, int index) -> {
            D data = method.run(context, index);
            return create(true, data.isValue() ? data.getLookahead() : 0);
        });
    }

    @SafeVarargs
    protected final M moptional(M... methods) {
        return moptional(m(methods));
    }

    protected M mfor(int amount, M method) {
        return create((C context, int index) -> {
            int lookahead = 0;

            for(int i = 0; i < amount; i++) {
                D data = method.run(context, index);

                if(!data.isValue())
                    return create(false, lookahead);

                lookahead += data.getLookahead();
            }

            return create(true, lookahead);
        });
    }

    @SafeVarargs
    protected final M mfor(int amount, M... methods) {
        return mfor(amount, m(methods));
    }

    protected M mignoreLookahead(M method) {
        return create((C context, int index) -> {
            D data = method.run(context, index);
            return create(data.isValue());
        });
    }

    @SafeVarargs
    protected final M mignoreLookahead(M... methods) {
        return mignoreLookahead(m(methods));
    }

    protected M mlookbehind(int amount, M method) {
        return create((C context, int index) -> {
            if(index-amount < 0) throw new IllegalStateException(String.format("Could not look behind index %d", index));
            return method.run(context, index-amount);
        });
    }

    protected M mlookbehind(M method) {
        return mlookbehind(1, method);
    }

    @SafeVarargs
    protected final M mlookbehind(int amount, M... methods) {
        return mlookbehind(amount, m(methods));
    }

    @SafeVarargs
    protected final M mlookbehind(M... methods) {
        return mlookbehind(m(methods));
    }
}
