////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.cli;

import lombok.Data;
import lombok.ToString;

@Data
public class CliArgs
{
    private final String[] argList;

    private boolean help;
    private boolean version;

    private String file;
    private Option option;

    public boolean hasOption()
    {
        return option != null;
    }

    public boolean hasFile()
    {
        return file != null;
    }

    public boolean isHelpAndVersion()
    {
        return help && version;
    }

    public void parse()
    {
        for (int i = 0; i < argList.length; i++)
        {
            if (handleArg(i)) i++;
        }
    }

    protected boolean handleArg(int index)
    {
        String arg = argList[index];

        switch (arg)
        {
            case "-h":
            case "--help":
                return handleHelp(index, arg);
            case "-v":
            case "--version":
                return handleVersion(index, arg);
            case "-c":
            case "--compile":
                return handleCompile(index, arg);
            case "-i":
            case "--interpret":
                return handleInterpret(index, arg);
            default:
                return handleNoFlag(index, arg);
        }
    }

    protected boolean handleHelp(int index, String arg)
    {
        if (!help)
        {
            help = true;
        }
        else
        {
            throw new IllegalArgumentException("Cannot pass the help parameter multiple times");
        }

        return false;
    }

    protected boolean handleVersion(int index, String arg)
    {
        if (!version)
        {
            version = true;
        }
        else
        {
            throw new IllegalArgumentException("Cannot pass the version parameter multiple times");
        }

        return false;
    }

    protected boolean handleCompile(int index, String arg)
    {
        if (option == null)
        {
            setOption(Option.COMPILE);
        }
        else
        {
            throw new IllegalArgumentException("Cannot use two run options");
        }

        return false;
    }

    protected boolean handleInterpret(int index, String arg)
    {
        if (option == null)
        {
            setOption(Option.INTERPRET);
        }
        else
        {
            throw new IllegalArgumentException("Cannot use two run options");
        }

        return false;
    }

    protected boolean handleNoFlag(int index, String arg)
    {
        if (file == null)
        {
            file = arg;
        }
        else
        {
            throw new IllegalArgumentException("Unexpected argument: " + arg);
        }

        return false;
    }

    @ToString
    public enum Option
    {
        COMPILE,
        INTERPRET
    }
}
