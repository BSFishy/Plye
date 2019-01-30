package com.fishy.provalang.ast;

import com.fishy.provalang.ast.api.AstNode;
import com.fishy.provalang.ast.api.nodes.AstContainer;
import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.api.scope.scopes.RootScope;
import com.fishy.provalang.ast.informers.Import;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Root extends AstNode
{
    public Root()
    {
        super(new RootScope());
    }

    public Package      packageName;
    public List<Import> imports = new ArrayList<>();
    public AstContainer container;

    @Override
    public boolean isPrepared()
    {
        return packageName != null && container != null;
    }

    @Override
    public Symbol expr()
    {
        return container.expr();
    }
}
