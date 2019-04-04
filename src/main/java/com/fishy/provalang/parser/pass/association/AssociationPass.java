package com.fishy.provalang.parser.pass.association;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.parser.pass.ParserPass;
import com.fishy.provalang.lexer.tokens.Separator;
import com.fishy.provalang.parser.pass.association.token.AssociationToken;
import com.fishy.provalang.parser.pass.association.token.BlockToken;
import com.fishy.provalang.parser.pass.association.token.StatementToken;

import java.util.ArrayList;
import java.util.List;

public class AssociationPass extends ParserPass<AssociationToken, LexerToken>
{
    @Override
    public List<AssociationToken> parse(List<LexerToken> tokens)
    {
        List<AssociationToken> output = new ArrayList<>();
        AssociationToken root = new StatementToken(null);

        for (LexerToken token : tokens)
        {
            assert root != null;
            if (token.type instanceof Separator.CurlyBracketOpen) // Should start a block
            {
                if (root instanceof StatementToken)
                {
                    root = ((StatementToken) root).promote();
                }
                else
                {
                    ProvalangApi.error("Unexpected token \"%s\" at %s", token.type.toString(), token.data.info.toString());
                }
            }
            else if (token.type instanceof Separator.CurlyBracketClose) {
                if(root instanceof StatementToken) {
                    ProvalangApi.error("Unexpected token \"%s\" at %s", token.type.toString(), token.data.info.toString());
                } else {
                    if(!root.hasParent()) {
                        output.add(root);
                        root = new StatementToken(null);
                    } else {
                        root = root.getParent();
                    }
                }
            }
            else if (token.type instanceof Separator.Semicolon) {
                if(!root.hasParent()) {
                    output.add(root);
                    root = new StatementToken(null);
                } else {
                    BlockToken parent = (BlockToken) root.getParent();
                    assert parent != null;
                    parent.addChild(root);
                    root = new StatementToken(parent);
                }
            }
        }

        return output;
    }
}
