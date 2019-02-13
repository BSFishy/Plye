package com.fishy.provalang.ast;

import lombok.Data;

@Data
public class Ast
{
    public final Root root;

    public void execute()
    {
        root.expr();
    }
}
