package com.fishy.provalang.api.parser.visitor;

import com.fishy.provalang.api.lexer.LexerToken;
import com.fishy.provalang.ast.api.AstNode;

import java.util.List;

public abstract class Visitor<T extends AstNode>
{
    public abstract boolean canVisit(List<LexerToken> tokens);

    public abstract T visit(List<LexerToken> tokens, AstNode visit);

    public abstract void clean(List<LexerToken> tokens);

    public void remove(int amount, List<LexerToken> tokens)
    {
        if (amount > 0) {tokens.subList(0, amount).clear();}
    }
}
