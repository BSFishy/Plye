package com.fishy.plye.api.parser.definer;

import com.fishy.plye.api.context.DefinerContext;
import com.fishy.plye.api.data.parser.definer.DefinitionData;
import com.fishy.plye.api.data.parser.definer.DefinitionResult;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

/**
 * A class that represents a definition for for a token. This simply stores the {@link DefinitionMethod}'s for token, and provides some helper methods for determining whether or
 * not a set of input tokens can be this token.
 *
 * @param <T> The type of token this class defines
 * @param <K> The type of token this class consumes
 */
@Data
public class Definition<T, K>
{
    @NotNull
    private final DefinitionMethod<K, T> method;
    @NotNull
    private final Supplier<T>            create;

    public DefinitionResult<T> run(List<K> tokens)
    {
        return run(tokens, 0);
    }

    public DefinitionResult<T> run(List<K> tokens, int index)
    {
        T token = create();

        DefinerContext<K, T> context = new DefinerContext<>();
        context.setCurrentToken(token);
        context.fill(tokens);

        DefinitionData data = method.run(context, index);

        return new DefinitionResult<>(context.getCurrentToken(), data);
    }

    public T create()
    {
        return create.get();
    }
}
