package com.fishy.provalang.cli;

import com.fishy.provalang.Provalang;
import com.fishy.provalang.api.cli.CliArgs;
import com.fishy.provalang.api.cli.ProvalangCliApi;

public class ProvalangCli
{

    public static void main(String[] args)
    {
        CliArgs arg = ProvalangCliApi.parseArgs(args);

        if (arg.getOption() == CliArgs.Option.INTERPRET)
        {
            Provalang.interpret(arg.getFile());
        }
        else if (arg.getOption() == CliArgs.Option.COMPILE)
        {
            throw new UnsupportedOperationException("Not implemented");
        }
        else
        {
            throw new IllegalStateException("Unknown option passed");
        }
    }
}
