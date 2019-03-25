package com.fishy.provalang.api.parser;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.ast.Ast;
import com.fishy.provalang.ast.Root;
import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.nodes.AstContainer;
import com.fishy.provalang.ast.informers.Import;
import com.fishy.provalang.ast.informers.Package;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public abstract class AbstractParser implements IParser
{
    protected List<LexToken> tokens;
    private boolean          prepared = false;

    public void prepare(List<LexToken> tokens)
    {
        this.tokens = tokens;

        ParserApi.addDefaultVisitors();

        prepared = true;
    }

    public AstNode visitNode(AstNode parent)
    {
        checkPrepared();

        return ParserApi.visit(tokens, parent);
    }

    public Ast visit()
    {
        checkPrepared();

        Package pkg = null;
        List<Import> imports = new ArrayList<>();

        Root r = new Root();

        while(!tokens.isEmpty())
        {
            AstNode node = visitNode(r);

            if(node instanceof Package)
            {
                if(!imports.isEmpty())
                    ProvalangApi.error("Error parsing: package must be first token");

                pkg = (Package) node;
            }
            else if(node instanceof Import)
            {
                if(pkg == null)
                    ProvalangApi.error("Error parsing: package must be first token");

                imports.add((Import) node);
            }
            else if(node instanceof AstContainer)
            {
                if(pkg == null)
                    ProvalangApi.error("Error parsing: package must be first command");

                r.packageName = pkg;
                r.imports = imports;
                r.container = (AstContainer) node;

                return new Ast(r);
            }
        }

        return null;
    }

    private void checkPrepared()
    {
        if(!prepared)
            ProvalangApi.error("Parser must be prepared before visiting");
    }
}
