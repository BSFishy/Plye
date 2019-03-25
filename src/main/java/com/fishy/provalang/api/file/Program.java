package com.fishy.provalang.api.file;

import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.api.lexer.TokenType;
import com.fishy.provalang.ast.Ast;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// TODO: use this class in LexerApi to allow for lookback
@Data
public class Program
{
    public final String     filename;
    public final FileReader reader;

    public final List<TokenType> tokenTypes = new ArrayList<>();
    public final List<LexToken>  tokens     = new ArrayList<>();

    private Ast ast;

    public Program(String filename)
    {
        this.filename = filename;
        this.reader = new FileReader(filename);
    }
}
