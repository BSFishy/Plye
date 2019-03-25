package com.fishy.provalang.parser.visitors.informers;

import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.api.parser.visitor.Visitor;
import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.informers.Package;
import com.fishy.provalang.lexer.tokens.Identifier;
import com.fishy.provalang.lexer.tokens.Keyword;
import com.fishy.provalang.lexer.tokens.Separator;

import java.util.List;

public class PackageVisitor extends Visitor<Package>
{
    @Override
    public boolean canVisit(List<LexToken> tokens)
    {
        return tokens.get(0).getType() instanceof Keyword.Package && (tokens.get(1).getType() instanceof Separator.Semicolon || (tokens.get(1).getType() instanceof Identifier && tokens.get(2).getType() instanceof Separator.Semicolon));
    }

    @Override
    public Package visit(List<LexToken> tokens, AstNode parent)
    {
        Package p = new Package(parent);
//        p.name = new PackageSymbol(tokens.get(1).getType() instanceof Separator.Semicolon ? "global" : ((Identifier.IdentifierData)tokens.get(1).getData()).value, p);

        return p;
    }

    @Override
    public void clean(List<LexToken> tokens)
    {
        remove(tokens.get(1).getType() instanceof Separator.Semicolon ? 2 : 3, tokens);
    }
}
