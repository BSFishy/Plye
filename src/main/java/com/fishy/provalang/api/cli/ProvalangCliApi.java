package com.fishy.provalang.api.cli;

import com.fishy.provalang.Provalang;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProvalangCliApi
{

    private static final Logger logger = Logger.getLogger("Provalang");

    public static CliArgs parseArgs(String[] args)
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
        log(Level.INFO, "Provalang %s\n", Provalang.VERSION);
    }

    public static void printHelp()
    {
        int cell = 20;

        printVersion();
        log(Level.INFO, "\tUsage: provalang [options] file\n\n");
        log(Level.INFO, "\t\t%sThe name of the file to run or compile\n\n", addBuffer("file", cell));
        log(Level.INFO, "\tOptions:\n");
        log(Level.INFO, "\t\t%sPrint this help information\n", addBuffer("-h, --help", cell));
        log(Level.INFO, "\t\t%sPrint the version information\n", addBuffer("-v, --version", cell));
        log(Level.INFO, "\t\t%sCompile the file\n", addBuffer("-c, --compile", cell));
        log(Level.INFO, "\t\t%sInterpret the file\n", addBuffer("-i, --interpret", cell));
    }

    public static void log(Level level, String format, Object... options)
    {
        //logger.log(level, format, options);
        System.out.printf(format, options);
    }

    public static void err(Level level, String format, Object... options)
    {
        System.err.printf(format, options);
    }

    private static String addBuffer(String s, int size)
    {
        return addBuffer(s, size, true);
    }

    private static String addBuffer(String s, int size, boolean right)
    {
        String buffer = repeat(" ", size - s.length());
        return right ? s + buffer : buffer + s;
    }

    private static String repeat(String str, int times)
    {
        return new String(new char[times]).replace("\0", str);
    }
}
