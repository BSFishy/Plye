package com.fishy.provalang;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.lexer.LexerToken;
import com.fishy.provalang.ast.Ast;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class Provalang
{

    public static final String VERSION = "0.1.0";

    public static List<LexerToken> lex(String code)
    {
        return ProvalangApi.getLexer().lex(code);
    }

    public static Ast parse(List<LexerToken> tokens)
    {
        return ProvalangApi.getParser().parse(tokens);
    }

    public static void interpret(String code)
    {
        List<LexerToken> lexed = lex(code);

        ProvalangApi.log(lexed.stream()
                              .map(l -> "" + l.getData().toString() + "")
                              .collect(Collectors.joining(", ", "[", "]")).replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t") + "\n");
        Ast ast = parse(lexed);

        ast.execute();
    }

    public static List<LexerToken> lexFile(String file)
    {
        return lex(readFile(file));
    }

    public static Ast parseFile(String file)
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
