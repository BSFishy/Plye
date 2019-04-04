package com.fishy.provalang.api.parser.definer;

import com.fishy.provalang.api.context.DefinerContext;
import com.fishy.provalang.api.data.parser.DefinitionData;
import com.fishy.provalang.api.matching.IMethod;

@FunctionalInterface
public interface DefinitionMethod extends IMethod<DefinitionData, DefinerContext>
{
}
