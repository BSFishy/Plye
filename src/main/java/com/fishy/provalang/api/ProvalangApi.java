package com.fishy.provalang.api;

import com.fishy.provalang.api.cli.ProvalangCliApi;
import com.fishy.provalang.api.lexer.ILexer;
import com.fishy.provalang.api.lexer.LexerToken;
import com.fishy.provalang.api.parser.IParser;
import com.fishy.provalang.api.util.Utils;
import com.fishy.provalang.lexer.Lexer;
import com.fishy.provalang.parser.Parser;

import java.util.logging.Level;

public class ProvalangApi
{
    public static ILexer lexer;
    public static IParser parser;

    public static void log(String format, Object... options)
    {
        ProvalangCliApi.log(Level.INFO, format, options);
    }

    public static void error(String format, Object... options)
    {
        log(format, options);
        System.exit(1);
    }

    public static void errorPretty(String error, LexerToken token, String code)
    {
        error("%s at line %d, column %d:\n\t%s\n\t%s%s\n", error, token.getData().getInfo().line + 1,
              token.getData().getInfo().column + 1, code.split("\n")[token.getData().getInfo().line],
              Utils.repeat(" ", token.getData().getInfo().column), Utils.repeat("^", token.getData().getInfo().length));
    }

    public static ILexer getLexer()
    {
        if (lexer == null)
            lexer = new Lexer();

        return lexer;
    }

    public static IParser getParser()
    {
        if(parser == null)
            parser = new Parser();

        return parser;
    }
}
