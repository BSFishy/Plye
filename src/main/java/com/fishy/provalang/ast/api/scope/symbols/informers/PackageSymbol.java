package com.fishy.provalang.ast.api.scope.symbols.informers;

import com.fishy.provalang.ast.api.scope.Symbol;
import com.fishy.provalang.ast.informers.Package;

public class PackageSymbol extends Symbol<Package>
{
    public PackageSymbol(String name, Package type)
    {
        super(name, type);
    }
}
