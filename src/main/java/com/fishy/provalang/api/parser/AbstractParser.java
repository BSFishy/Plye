package com.fishy.provalang.api.parser;

import com.fishy.provalang.api.ProvalangApi;
import com.fishy.provalang.api.data.parser.PassResult;
import com.fishy.provalang.api.lexer.LexToken;
import com.fishy.provalang.api.parser.pass.ParserPass;
import com.fishy.provalang.api.parser.pass.PassSorter;
import com.fishy.provalang.api.parser.pass.PassToken;
import com.fishy.provalang.parser.pass.containerization.LexerToken;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public abstract class AbstractParser implements Parser
{
    private final List<ParserPass<? extends PassToken, ? extends PassToken>> passes = new ArrayList<>();

    public void addPass(@NotNull ParserPass<? extends PassToken, ? extends PassToken> pass) {
        if(!passes.contains(pass))
            passes.add(pass);
    }

    public void addPasses(@NotNull Collection<ParserPass<? extends PassToken, ? extends PassToken>> passes) {
        for(ParserPass<? extends PassToken, ? extends PassToken> pass : passes) {
            addPass(pass);
        }
    }

    public abstract void addDefaultPasses();

    public void preparePasses() {
        for(ParserPass pass : passes) {
            pass.defaultDefiners();
        }
    }

    public <T extends PassToken> List<T> run(@NotNull List<LexToken> tokens) {
        List<T> result = new ArrayList<>();
        List<? extends PassToken> currentTokens = LexerToken.convert(tokens);
        Class<? extends ParserPass> previousPass = null;
        PassSorter.sort(passes);
        int index = 0;

        while(index <= tokens.size()) {
            boolean found = false;

            for(ParserPass<? extends PassToken, ? extends PassToken> pass : passes) {
                if(pass.previousPass() == null || Objects.equals(pass.previousPass(), previousPass)) {
                    PassResult<? extends PassToken> res = pass.parseGeneric(currentTokens);

                    index += res.getLength();
                    currentTokens = res.getTokens();
                    previousPass = pass.getClass();

                    found = true;
                    break;
                }
            }

            if(!found) {
                ProvalangApi.error("Error parsing: %s", currentTokens.get(index));
            }
        }

        return result;
    }
}
