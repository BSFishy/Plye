package com.fishy.provalang.ast.expressions;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.ast.api.nodes.AstExpression;
import com.fishy.provalang.ast.api.nodes.ICallableBlock;
import com.fishy.provalang.ast.api.nodes.Type;
import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.informers.Identifier;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class Call extends AstExpression
{

    public Identifier   identifier;
    public List<Symbol> arguments;

    @Override
    public boolean isPrepared()
    {
        return super.isPrepared();
    }

    @Override
    public Symbol expr()
    {
        Type t = identifier.get();
        if(!(t instanceof ICallableBlock))
            ProvalangApi.error("Cannot call a non-callable block: %s", t.getName());

        return ((ICallableBlock)t).run(arguments);
    }
}
