////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye;

import com.fishy.plye.api.language.LanguageApi;
import com.fishy.plye.file.ProgramFile;
import org.jetbrains.annotations.NotNull;

public class Plye
{

    public static final String VERSION = "@VERSION@";

    public static ProgramFile parse(@NotNull String filename)
    {
        ProgramFile file = lex(filename);
        parse(file);
        return file;
    }

    public static void parse(@NotNull ProgramFile file)
    {
        LanguageApi.getDefaultLanguage().parser();
        LanguageApi.getDefaultLanguage().parse(file);
    }

    public static ProgramFile lex(@NotNull String filename)
    {
        ProgramFile file = new ProgramFile(filename);
        lex(file);
        return file;
    }

    public static void lex(@NotNull ProgramFile file) {
        LanguageApi.getDefaultLanguage().lexer();
        LanguageApi.getDefaultLanguage().lex(file);
    }

    public static void interpret(String filename)
    {
        ProgramFile file = parse(filename);
    }

    public static void compile(String filename)
    {
        ProgramFile file = parse(filename);

//        PlyeApi.log(programFile.getTokens().stream()
//                                .map(l -> "" + l.getData().toString() + "")
//                                .collect(Collectors.joining(", ", "[", "]")).replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t") + "\n");

        // TODO: do the codegen here
    }
}
