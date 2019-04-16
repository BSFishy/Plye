package com.fishy.provalang.api.parser.definer;

import com.fishy.provalang.api.context.DefinerContext;
import com.fishy.provalang.api.data.parser.definer.DefinitionData;
import com.fishy.provalang.api.matching.IMethod;

/**
 * A method that is used to match tokens when defining a token. This is meant to be used as a lambda to make writing new custom methods for matching extremely simple. It also allows for much more flexibility when manipulating the methods, their inputs and outputs, and a plethora of other stuff.
 * @param <T> The token that is used as input
 */
@FunctionalInterface
public interface DefinitionMethod<T, K> extends IMethod<DefinitionData, DefinerContext<T, K>>
{
}
