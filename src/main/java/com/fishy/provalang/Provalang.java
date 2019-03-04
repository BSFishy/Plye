package com.fishy.provalang;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.file.Program;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

public class Provalang
{

    public static final String VERSION = "@VERSION@";

    public static Program lex(String filename)
    {
        Program p = new Program(filename);
        ProvalangApi.getLexer().lex(p);
        return p;
    }

    public static Program parse(Program program)
    {
        ProvalangApi.getParser().parse(program);
        return program;
    }

    public static void interpret(String code)
    {
        Program program = lex(code);

        ProvalangApi.log(program.getTokens().stream()
                              .map(l -> "" + l.getData().toString() + "")
                              .collect(Collectors.joining(", ", "[", "]")).replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t") + "\n");
        parse(program);

        program.getAst().execute();
    }

    public static Program lexFile(String file)
    {
        return lex(readFile(file));
    }

    public static Program parseFile(String file)
    {
        return parse(lexFile(file));
    }

    public static void interpretFile(String file)
    {
        interpret(readFile(file));
    }

    public static String readFile(String file)
    {
        File   f        = new File(file);
        String contents = "";

        try
        {
            contents = new String(Files.readAllBytes(f.toPath()));
        }
        catch (IOException e)
        {
            ProvalangApi.error("Error: Unable to read file: %s", file);
        }

        return contents;
    }
}
