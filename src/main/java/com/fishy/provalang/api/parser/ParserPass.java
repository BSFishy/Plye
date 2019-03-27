package com.fishy.provalang.api.parser;

import java.util.ArrayList;
import java.util.List;

public abstract class ParserPass
{
    private final List<NodeHandler> handlers = new ArrayList<>();

    public abstract void addDefaultHandlers();

    public void addHandler(NodeHandler handler)
    {
        handlers.add(handler);
    }

    public List<NodeHandler> getHandlers()
    {
        return handlers;
    }
}
