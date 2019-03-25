package com.fishy.provalang.api.parser.visitor;

import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.ast.api.AstNode;

import java.util.List;

public abstract class Visitor<T extends AstNode>
{
    public abstract boolean canVisit(List<LexToken> tokens);

    public abstract T visit(List<LexToken> tokens, AstNode visit);

    public abstract void clean(List<LexToken> tokens);

    public void remove(int amount, List<LexToken> tokens)
    {
        if (amount > 0) {tokens.subList(0, amount).clear();}
    }
}
