package com.fishy.plye.api.parser;

import com.fishy.plye.api.lexer.LexToken;
import com.fishy.plye.api.parser.pass.PassToken;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface Parser
{
    <T extends PassToken> List<T> parse(@NotNull List<LexToken> tokens);
}
