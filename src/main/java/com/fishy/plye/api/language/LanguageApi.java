////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.language;

import com.fishy.plye.language.PlyeLanguage;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LanguageApi
{
    public static final List<Language> languages = new ArrayList<>();
    public static Language defaultLanguage;

    public static void addLanguage(@NotNull Language language) {
        addLanguage(languages, language);
    }

    public static void addLanguages(@NotNull Collection<Language> languages) {
        addLanguages(LanguageApi.languages, languages);
    }

    public static void addLanguage(@NotNull List<Language> languages, @NotNull Language language) {
        if(!languages.contains(language))
            languages.add(language);
    }

    public static void addLanguages(@NotNull List<Language> languageList, @NotNull Collection<Language> languages) {
        languages.forEach(language -> addLanguage(languageList, language));
    }

    @NotNull
    public static Language getDefaultLanguage() {
        if(defaultLanguage == null)
            defaultLanguage = new PlyeLanguage();

        return defaultLanguage;
    }
}
