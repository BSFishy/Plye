////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.parser.pass;

import com.fishy.plye.api.PlyeApi;
import com.fishy.plye.api.data.parser.PassResult;
import com.fishy.plye.api.data.parser.definer.DefinitionResult;
import com.fishy.plye.api.parser.definer.Definer;
import com.fishy.plye.api.parser.definer.Definition;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class ParserPass<T extends PassToken, K extends PassToken>
{
    private final List<Definition<? extends T, K>> definers = new ArrayList<>();

    @Nullable
    private final Class<? extends ParserPass> previous;
    @NotNull
    private final Class<K>                    kclass;

    public ParserPass(@Nullable Class<? extends ParserPass> previous, @NotNull Class<K> kclass)
    {
        this.previous = previous;
        this.kclass = kclass;
    }

    public void addDefiner(@NotNull Definer<? extends T, K> definer)
    {
        definers.add(definer.getDefinition());
    }

    public void addDefiner(@NotNull Definition<? extends T, K> definer)
    {
        definers.add(definer);
    }

    @Contract(value = "_ -> new", pure = true)
    protected PassResult<T> run(@NotNull List<K> tokens)
    {
        List<T> result = new ArrayList<>();
        int     index  = 0;

        while (index < tokens.size())
        {
            boolean found = false;

            for (Definition<? extends T, K> def : definers)
            {
                DefinitionResult<? extends T> res = def.run(tokens, index);

                if (res.getValue())
                {
                    result.add(res.getToken());

                    index += res.getLookahead();
                    found = true;

                    PlyeApi.log(res.toString() + "\n");

                    break;
                }
            }

            if (!found)
            {
                PlyeApi.error("Error parsing: %s\n", tokens.get(index).toString());

                break;
            }
        }

        return new PassResult<>(index >= tokens.size(), result, index);
    }

    @Nullable
    public Class<? extends ParserPass> previousPass()
    {
        return previous;
    }

    public abstract void defaultDefiners();

    @Contract(value = "_ -> new", pure = true)
    public List<K> convertList(@NotNull List<? extends PassToken> tks) {
        List<K> tokens = new ArrayList<>();

        for (PassToken token : tks)
        {
            try
            {
                tokens.add(outputClass().cast(token));
            }
            catch (ClassCastException ignored)
            {
            }
        }

        return tokens;
    }

    @Contract(value = "_ -> new", pure = true)
    public PassResult<T> parseGeneric(@NotNull List<? extends PassToken> currentTokens)
    {
        return parse(convertList(currentTokens));
    }

    @NotNull
    public Class<K> outputClass()
    {
        return kclass;
    }

    @Contract(value = "_ -> new", pure = true)
    public abstract PassResult<T> parse(List<K> tokens);
}
