package com.fishy.provalang.api.parser.pass;

import java.util.List;

public abstract class ParserPass<T extends PassToken, K extends PassToken>
{
    public abstract List<T> parse(List<K> tokens);
}
