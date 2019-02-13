package com.fishy.provalang.parser.visitors.informers;

import com.fishy.provalang.api.lexer.LexerToken;
import com.fishy.provalang.api.lexer.types.Identifier;
import com.fishy.provalang.api.lexer.types.Keyword;
import com.fishy.provalang.api.lexer.types.Separator;
import com.fishy.provalang.api.parser.visitor.Visitor;
import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.scope.symbols.informers.ImportSymbol;
import com.fishy.provalang.ast.informers.Import;

import java.util.List;

public class ImportVisitor extends Visitor<Import>
{
    @Override
    public boolean canVisit(List<LexerToken> tokens)
    {
        return tokens.get(0).getType() instanceof Keyword.Import && tokens.get(1).getType() instanceof Identifier && tokens.get(2).getType() instanceof Separator.Semicolon;
    }

    @Override
    public Import visit(List<LexerToken> tokens, AstNode parent)
    {
        Import i = new Import(parent);
        i.name = new ImportSymbol(((Identifier.IdentifierData)tokens.get(1).getData()).value, i);

        return i;
    }

    @Override
    public void clean(List<LexerToken> tokens)
    {
        remove(3, tokens);
    }
}
