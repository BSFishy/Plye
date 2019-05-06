////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.language;

import com.fishy.plye.api.language.Parse;
import com.fishy.plye.api.language.PassHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlyeParse extends Parse
{
    public PlyeParse()
    {
        super(PlyeLanguage.NAME, new PlyePassHandler());
    }
}
