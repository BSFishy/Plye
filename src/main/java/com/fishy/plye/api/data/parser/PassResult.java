package com.fishy.plye.api.data.parser;

import lombok.Data;

import java.util.List;

@Data
public class PassResult<T>
{
    public final List<T> tokens;
    public final int length;
}
