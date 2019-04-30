package com.fishy.plye;

import com.fishy.plye.api.PlyeApi;
import com.fishy.plye.file.Program;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Plye
{

    public static final String VERSION = "@VERSION@";

    public static void interpret(String file)
    {
        Program program = parse(file);

//        PlyeApi.log(program.getTokens().stream()
//                                .map(l -> "" + l.getData().toString() + "")
//                                .collect(Collectors.joining(", ", "[", "]")).replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t") + "\n");

//        program.getAst().execute();
    }

    public static Program parse(String file)
    {
        return parse(lex(file));
    }

    @Contract("_ -> param1")
    public static Program parse(@NotNull Program program)
    {
        PlyeApi.log(PlyeApi.getParser().parse(program.getTokens()).toString() + "\n");
        return program;
    }

    public static Program lex(String filename)
    {
        Program p = new Program(filename);
        PlyeApi.getLexer().lex(p);

        try
        {
            p.getReader().close();
        }
        catch (IOException e)
        {
            PlyeApi.error("Unable to close file (%s): %s\n", filename, e.getMessage());
        }

        return p;
    }

    public static void compile(String file)
    {
        Program program = parse(file);

//        PlyeApi.log(program.getTokens().stream()
//                                .map(l -> "" + l.getData().toString() + "")
//                                .collect(Collectors.joining(", ", "[", "]")).replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t") + "\n");

    }
}
