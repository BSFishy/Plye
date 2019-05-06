////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.parser.definer.interaction;

import com.fishy.plye.api.data.parser.definer.DefinitionResult;
import com.fishy.plye.api.parser.definer.Definition;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DefaultInteraction extends DefinerInteraction
{
    public static final DefaultInteraction instance = new DefaultInteraction();

    @Override
    public <T, K> DefinitionResult<T> run(@NotNull Definition<T, K> definition, @NotNull List<K> tokens, int index)
    {
        return definition.run(tokens, index);
    }
}
