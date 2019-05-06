////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api;

import com.fishy.plye.api.cli.PlyeCliApi;
import com.fishy.plye.api.lexer.ILexer;
import com.fishy.plye.api.lexer.LexToken;
import com.fishy.plye.api.parser.Parser;
import com.fishy.plye.lexer.Lexer;
import com.fishy.plye.utils.Utils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;

public class PlyeApi
{
    public static void log(@NotNull String format, @NotNull Object... options)
    {
        PlyeCliApi.log(Level.INFO, format, options);
    }

    public static void error(@NotNull String message, @NotNull Exception e, @NotNull Object... options)
    {
        err(message, e, options);
        System.exit(1);
    }

    public static void err(@NotNull String message, @NotNull Exception e, @NotNull Object... options)
    {
        PlyeCliApi.err(Level.SEVERE, message, e, options);
    }

    @SuppressWarnings("FeatureEnvy")
    public static void errorPretty(@NotNull String error, @NotNull LexToken token, @NotNull String code)
    {
        error("%s at line %d, column %d:\n\t%s\n\t%s%s\n", error, token.getData().getInfo().line + 1,
              token.getData().getInfo().column + 1, code.split("\n")[token.getData().getInfo().line],
              Utils.repeat(" ", token.getData().getInfo().column), Utils.repeat("^", token.getData().getInfo().length));
    }

    public static void error(@NotNull String format, @NotNull Object... options)
    {
        err(format, options);
        System.exit(1);
    }

    public static void err(@NotNull String format, @NotNull Object... options)
    {
        PlyeCliApi.err(Level.SEVERE, format, options);
    }
}
