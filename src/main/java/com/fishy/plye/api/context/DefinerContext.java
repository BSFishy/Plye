////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.context;

import com.fishy.plye.api.PlyeApi;
import com.fishy.plye.api.data.parser.definer.PersistentData;
import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides information to {@link com.fishy.plye.api.parser.definer.Definer}'s while they are matching. This can provide them with {@link PersistentData} or a list of
 * input tokens.
 *
 * @param <T> The type of input token
 */
@Data
public class DefinerContext<T, K>
{
    private final List<T>              tokens = new ArrayList<>();
    private final List<PersistentData> data   = new ArrayList<>();
    private       K                    currentToken;

    public void fill(List<T> tokens)
    {
        this.tokens.addAll(tokens);
    }

    public T get(int index)
    {
        return tokens.get(index);
    }

    public void addData(PersistentData data)
    {
        this.data.add(data);
    }

    public <U extends PersistentData> U get(Class<U> c)
    {
        for (PersistentData d : data)
        {
            if (c.isInstance(d))
                return c.cast(d);
        }

        U instance;
        try
        {
            instance = c.getConstructor().newInstance();
            data.add(instance);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
        {
            PlyeApi.log("Warning: Could not create an instance of \"%s\", because it did not have a no-args constructor.", c.getSimpleName());
            instance = null;
        }
        return instance;
    }
}
