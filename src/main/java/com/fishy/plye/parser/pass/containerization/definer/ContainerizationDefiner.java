package com.fishy.plye.parser.pass.containerization.definer;

import com.fishy.plye.api.context.DefinerContext;
import com.fishy.plye.api.data.parser.definer.DefinitionData;
import com.fishy.plye.api.lexer.TokenType;
import com.fishy.plye.api.parser.definer.Definer;
import com.fishy.plye.api.parser.definer.MethodWrapper;
import com.fishy.plye.parser.pass.containerization.LexerToken;
import com.fishy.plye.parser.pass.containerization.token.ContainerizationToken;
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
