package com.fishy.provalang.api.parser;

import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.api.parser.pass.PassToken;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Parser
{
    <T extends PassToken> List<T> parse(@NotNull List<LexToken> tokens);
}
