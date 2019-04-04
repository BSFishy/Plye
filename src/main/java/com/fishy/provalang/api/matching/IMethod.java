package com.fishy.provalang.api.matching;

import com.fishy.provalang.api.data.AbstractMatchingData;

@FunctionalInterface
public interface IMethod<T extends AbstractMatchingData, C>
{
    T run(C context, int index);
}
