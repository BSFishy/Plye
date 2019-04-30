////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.cli;

import com.fishy.plye.Plye;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PlyeCliApi
{

    private static final Logger logger = Logger.getLogger("Plye");

    @SuppressWarnings("FeatureEnvy")
    public static CliArgs parseArgs(@NotNull String[] args)
    {
        CliArgs output = new CliArgs(args);

        output.parse();

        if (output.isHelpAndVersion())
        {
            throw new IllegalArgumentException("Cannot use help and version argument");
        }

        if (output.isHelp())
        {
            printHelp();
            System.exit(0);
        }

        if (output.isVersion())
        {
            printVersion();
            System.exit(0);
        }

        if (!output.hasOption())
        {
            throw new IllegalArgumentException("No option specified");
        }

        if (!output.hasFile())
        {
            throw new IllegalArgumentException("No file specified");
        }

        return output;
    }

    public static void printVersion()
    {
        log(Level.INFO, "Plye %s\n", Plye.VERSION);
    }

    public static void printHelp()
    {
        int cell = 20;

        printVersion();
        log(Level.INFO, "\tUsage: plye [options] file\n\n");
        log(Level.INFO, "\t\t%sThe name of the file to run or compile\n\n", addBuffer("file", cell));
        log(Level.INFO, "\tOptions:\n");
        log(Level.INFO, "\t\t%sPrint this help information\n", addBuffer("-h, --help", cell));
        log(Level.INFO, "\t\t%sPrint the version information\n", addBuffer("-v, --version", cell));
        log(Level.INFO, "\t\t%sCompile the file\n", addBuffer("-c, --compile", cell));
        log(Level.INFO, "\t\t%sInterpret the file\n", addBuffer("-i, --interpret", cell));
    }

    public static void log(@NotNull Level level, @NotNull String format, @NotNull Object... options)
    {
        //logger.log(level, format, options);
        System.out.printf(format, options);
    }

    public static void err(@NotNull Level level, @NotNull String format, @NotNull Object... options)
    {
        System.err.printf(format, options);
    }

    public static void err(@NotNull Level level, @NotNull String format, @NotNull Exception e, @NotNull Object... options)
    {
        System.err.printf(format, options);
//        System.err.println(e.toString());
        e.printStackTrace(System.err);
    }

    @NotNull
    private static String addBuffer(@NotNull String s, int size)
    {
        return addBuffer(s, size, true);
    }

    @NotNull
    private static String addBuffer(@NotNull String s, int size, boolean right)
    {
        String buffer = repeat(" ", size - s.length());
        return right ? s + buffer : buffer + s;
    }

    @NotNull
    private static String repeat(@NotNull String str, int times)
    {
        return new String(new char[times]).replace("\0", str);
    }
}
