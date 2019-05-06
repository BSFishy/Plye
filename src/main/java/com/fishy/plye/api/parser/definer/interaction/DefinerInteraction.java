////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.parser.definer.interaction;

import com.fishy.plye.api.data.parser.definer.DefinitionResult;
import com.fishy.plye.api.parser.definer.Definition;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class DefinerInteraction
{
    public abstract <T, K> DefinitionResult<T> run(@NotNull Definition<T, K> definition, @NotNull List<K> tokens, int index);
}
