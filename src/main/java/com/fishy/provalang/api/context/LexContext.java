package com.fishy.provalang.api.context;

import com.fishy.provalang.api.file.FileWrapper;
import lombok.Data;

@Data
public class LexContext
{
    public final FileWrapper wrapper;
}
