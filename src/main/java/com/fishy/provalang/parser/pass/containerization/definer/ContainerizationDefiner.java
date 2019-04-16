package com.fishy.provalang.parser.pass.containerization.definer;

import com.fishy.provalang.api.context.DefinerContext;
import com.fishy.provalang.api.data.parser.definer.DefinitionData;
import com.fishy.provalang.api.lexer.TokenType;
import com.fishy.provalang.api.parser.definer.Definer;
import com.fishy.provalang.api.parser.definer.MethodWrapper;
import com.fishy.provalang.parser.pass.containerization.LexerToken;
import com.fishy.provalang.parser.pass.containerization.token.ContainerizationToken;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public abstract class ContainerizationDefiner extends Definer<ContainerizationToken, LexerToken>
{

    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
    public final MethodWrapper<LexerToken, ContainerizationToken> m(TokenType token, @Nullable BiFunction<ContainerizationToken, LexerToken, ContainerizationToken> function)
    {
        return create(
                (DefinerContext<LexerToken, ContainerizationToken> context, int index) -> new DefinitionData(token.getClass().isInstance(context.get(index).getType()), 1),
                function);
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public final MethodWrapper<LexerToken, ContainerizationToken> m(TokenType token)
    {
        return m(token, null);
    }
}
