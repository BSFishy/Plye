////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.cli;

import com.fishy.plye.Plye;
import com.fishy.plye.api.cli.CliArgs;
import com.fishy.plye.api.cli.PlyeCliApi;

public class PlyeCli
{

    @SuppressWarnings("FeatureEnvy")
    public static void main(String[] args)
    {
        CliArgs arg = PlyeCliApi.parseArgs(args);

        if (arg.getOption() == CliArgs.Option.INTERPRET)
        {
            Plye.interpret(arg.getFile());
        }
        else if (arg.getOption() == CliArgs.Option.COMPILE)
        {
            Plye.compile(arg.getFile());
        }
        else
        {
            throw new IllegalStateException("Unknown option passed");
        }
    }
}
