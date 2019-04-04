package com.fishy.provalang.api.context;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DefinerContext<T>
{
    private final List<T> tokens = new ArrayList<>();

    public T get(int index) {
        return tokens.get(index);
    }
}
