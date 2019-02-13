package com.fishy.provalang.ast.api.scope.symbols.informers;

import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.informers.Import;

public class ImportSymbol extends Symbol<Import>
{
    public ImportSymbol(String name, Import type)
    {
        super(name, type);
    }
}
