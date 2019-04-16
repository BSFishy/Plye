package com.fishy.provalang.api.parser.pass;

import com.fishy.provalang.api.parser.definer.Definer;
import com.fishy.provalang.api.parser.definer.Definition;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class ParserPass<T extends PassToken, K extends PassToken>
{
    private final List<Definition> definers = new ArrayList<>();

    public void addDefiner(@NotNull Definer definer) {
        definers.add(definer.define());
    }

    public void addDefiner(@NotNull Definition definer) {
        definers.add(definer);
    }

    protected List<T> run(List<K> tokens) {
        List<T> result = new ArrayList<>();

        for(Definition def : definers) {
            // TODO: work on finishing up this
        }

        return result;
    }

    public abstract void defaultDefiners();

    public abstract List<T> parse(List<K> tokens);
}
