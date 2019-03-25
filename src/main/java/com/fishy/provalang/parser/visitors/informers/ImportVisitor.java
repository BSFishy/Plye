package com.fishy.provalang.parser.visitors.informers;

import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.api.parser.visitor.Visitor;
import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.informers.Import;
import com.fishy.provalang.lexer.tokens.Identifier;
import com.fishy.provalang.lexer.tokens.Keyword;
import com.fishy.provalang.lexer.tokens.Separator;

import java.util.List;

public class ImportVisitor extends Visitor<Import>
{
    @Override
    public boolean canVisit(List<LexToken> tokens)
    {
        return tokens.get(0).getType() instanceof Keyword.Import && tokens.get(1).getType() instanceof Identifier && tokens.get(2).getType() instanceof Separator.Semicolon;
    }

    @Override
    public Import visit(List<LexToken> tokens, AstNode parent)
    {
        Import i = new Import(parent);
//        i.name = new ImportSymbol(((Identifier.IdentifierData)tokens.get(1).getData()).value, i);

        return i;
    }

    @Override
    public void clean(List<LexToken> tokens)
    {
        remove(3, tokens);
    }
}
