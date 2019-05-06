////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.file;

import com.fishy.plye.api.PlyeApi;
import com.fishy.plye.api.ast.AstNode;
import com.fishy.plye.api.lexer.LexToken;
import com.fishy.plye.api.lexer.TokenType;
import lombok.Data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProgramFile
{
    public final String     filename;
    public final FileReader reader;

    public final List<TokenType> tokenTypes = new ArrayList<>();
    public final List<LexToken>  tokens     = new ArrayList<>();

    public final List<AstNode> nodes = new ArrayList<>();

    private boolean lexed = false;
    private boolean parsed = false;

    public ProgramFile(String filename)
    {
        this.filename = filename;
        this.reader = new FileReader(filename);
    }

    public boolean shouldLex() {
        return !lexed;
    }

    public void finalizeLex()
    {
        try
        {
            reader.close();
        }
        catch (IOException e)
        {
            PlyeApi.error("Could not close file %s: %s", e, filename, e.getMessage());
        }

        lexed = true;
    }

    public boolean shouldParse() {
        return !parsed;
    }

    public void finalizeParse() {
        tokenTypes.clear();
        tokens.clear();

        parsed = true;
    }
}
