package com.fishy.provalang.api.parser;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.lexer.LexerToken;
import com.fishy.provalang.ast.Ast;
import com.fishy.provalang.ast.Root;
import com.fishy.provalang.ast.api.AstNode;
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
    protected List<LexerToken> tokens;
    private boolean prepared = false;

    public void prepare(List<LexerToken> tokens)
    {
        this.tokens = tokens;
        prepared = true;
    }

    public AstNode visitNode()
    {
        checkPrepared();

        return ParserApi.visit(tokens);
    }

    public Ast visit()
    {
        checkPrepared();

        Package pkg;
        List<Import> imports = new ArrayList<>();

        while(!tokens.isEmpty())
        {
            AstNode node = visitNode();

            if(node instanceof Package)
            {
                if(!imports.isEmpty())
                    ProvalangApi.error("Error parsing: package is not the first token");

                pkg = (Package) node;
            }
            else if(node instanceof Import)
            {

            }
            else if(node instanceof Root)
            {

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
