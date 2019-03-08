package com.fishy.provalang;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.file.Program;

import java.io.IOException;
import java.util.stream.Collectors;

public class Provalang
{

    public static final String VERSION = "@VERSION@";

    public static Program lex(String filename)
    {
        Program p = new Program(filename);
        ProvalangApi.getLexer().lex(p);

        try
        {
            p.getReader().close();
        }
        catch (IOException e)
        {
            ProvalangApi.error("Unable to close file (%s): %s", filename, e.getMessage());
        }

        return p;
    }

    public static Program parse(Program program)
    {
        ProvalangApi.getParser().parse(program);
        return program;
    }

    public static Program parse(String file)
    {
        return parse(lex(file));
    }

    public static void interpret(String file)
    {
        Program program = parse(file);

        ProvalangApi.log(program.getTokens().stream()
                                .map(l -> "" + l.getData().toString() + "")
                                .collect(Collectors.joining(", ", "[", "]")).replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t") + "\n");

        program.getAst().execute();
    }

    public static void compile(String file)
    {
        Program program = parse(file);

        ProvalangApi.log(program.getTokens().stream()
                                .map(l -> "" + l.getData().toString() + "")
                                .collect(Collectors.joining(", ", "[", "]")).replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t") + "\n");

    }
}
