////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.language;

import com.fishy.plye.file.ProgramFile;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

@Data
public abstract class PassHandler
{
    private final String name;

    public abstract void preparePasses();

    public abstract void passes(@NotNull ProgramFile file);
}
