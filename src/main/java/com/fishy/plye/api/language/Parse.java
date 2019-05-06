////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.language;

import lombok.Data;

@Data
public abstract class Parse
{
    private final String name;
    private final PassHandler passHandler;

    public void passes() {
        passHandler.preparePasses();
    }
}
