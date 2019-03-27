package com.fishy.provalang.api.file;

import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.api.lexer.TokenType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Program
{
    public final String     filename;
    public final FileReader reader;

    public final List<TokenType> tokenTypes = new ArrayList<>();
    public final List<LexToken>  tokens     = new ArrayList<>();

    public Program(String filename)
    {
        this.filename = filename;
        this.reader = new FileReader(filename);
    }
}
