package com.fishy.provalang.parser.visitors.informers;

import com.fishy.provalang.api.lexer.LexerToken;
import com.fishy.provalang.api.lexer.types.Identifier;
import com.fishy.provalang.api.lexer.types.Keyword;
import com.fishy.provalang.api.lexer.types.Separator;
import com.fishy.provalang.api.parser.visitor.Visitor;
import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.scope.symbols.informers.PackageSymbol;
import com.fishy.provalang.ast.informers.Package;

import java.util.List;

public class PackageVisitor extends Visitor<Package>
{
    @Override
    public boolean canVisit(List<LexerToken> tokens)
    {
        return tokens.get(0).getType() instanceof Keyword.Package && (tokens.get(1).getType() instanceof Separator.Semicolon || (tokens.get(1).getType() instanceof Identifier && tokens.get(2).getType() instanceof Separator.Semicolon));
    }

    @Override
    public Package visit(List<LexerToken> tokens, AstNode parent)
    {
        Package p = new Package(parent);
        p.name = new PackageSymbol(tokens.get(1).getType() instanceof Separator.Semicolon ? "global" : ((Identifier.IdentifierData)tokens.get(1).getData()).value, p);

        return p;
    }

    @Override
    public void clean(List<LexerToken> tokens)
    {
        remove(tokens.get(1).getType() instanceof Separator.Semicolon ? 2 : 3, tokens);
    }
}
