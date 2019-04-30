////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.matching;

import com.fishy.plye.api.data.AbstractMatchingData;

@FunctionalInterface
public interface IMethod<T extends AbstractMatchingData, C>
{
    T run(C context, int index);
}
