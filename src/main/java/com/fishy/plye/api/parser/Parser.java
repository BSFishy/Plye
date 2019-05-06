////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.parser;

import com.fishy.plye.api.language.PassHandler;
import com.fishy.plye.file.ProgramFile;
import org.jetbrains.annotations.NotNull;

public interface Parser
{
    void parse(@NotNull ProgramFile file, @NotNull PassHandler handler);
}
