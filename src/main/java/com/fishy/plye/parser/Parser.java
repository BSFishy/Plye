////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.parser;

import com.fishy.plye.api.language.PassHandler;
import com.fishy.plye.api.parser.AbstractParser;
import com.fishy.plye.file.ProgramFile;
import org.jetbrains.annotations.NotNull;

public class Parser extends AbstractParser
{
    @Override
    public void parse(@NotNull ProgramFile file, @NotNull PassHandler handler)
    {
        handler.passes(file);
    }
}
