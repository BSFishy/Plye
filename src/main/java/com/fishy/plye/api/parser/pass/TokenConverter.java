package com.fishy.plye.api.parser.pass;

import java.util.List;

public abstract class TokenConverter<T extends PassToken, K extends PassToken>
{
    public abstract List<T> convert(List<K> tokens);
}
