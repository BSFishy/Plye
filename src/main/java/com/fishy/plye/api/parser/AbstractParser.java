package com.fishy.plye.api.parser;

import com.fishy.plye.api.PlyeApi;
import com.fishy.plye.api.ast.AstNode;
import com.fishy.plye.api.data.parser.PassResult;
import com.fishy.plye.api.lexer.LexToken;
import com.fishy.plye.api.parser.pass.ParserPass;
import com.fishy.plye.api.parser.pass.PassSorter;
import com.fishy.plye.api.parser.pass.PassToken;
import com.fishy.plye.parser.pass.containerization.LexerToken;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public abstract class AbstractParser implements Parser
{
    private final List<ParserPass<? extends PassToken, ? extends PassToken>> passes = new ArrayList<>();

    public void addPasses(@NotNull Collection<ParserPass<? extends PassToken, ? extends PassToken>> passes)
    {
        for (ParserPass<? extends PassToken, ? extends PassToken> pass : passes)
        {
            addPass(pass);
        }
    }

    public void addPass(@NotNull ParserPass<? extends PassToken, ? extends PassToken> pass)
    {
        if (!passes.contains(pass))
            passes.add(pass);
    }

    public abstract void addDefaultPasses();

    public void preparePasses()
    {
        for (ParserPass pass : passes)
        {
            pass.defaultDefiners();
        }
    }

    public List<AstNode> run(@NotNull List<LexToken> tokens)
    {
        List<? extends PassToken>   currentTokens     = LexerToken.convert(tokens);
        Class<? extends ParserPass> previousPassClass = null;
        PassSorter.sort(passes);
        int index = 0;

        while (index < tokens.size())
        {
            boolean found = false;

            for (ParserPass<? extends PassToken, ? extends PassToken> pass : passes)
            {
                if (pass.previousPass() == null || Objects.equals(pass.previousPass(), previousPassClass))
                {
                    PassResult<? extends PassToken> res = pass.parseGeneric(currentTokens);
                    found = res.isFull();

                    if (found)
                    {
                        index += res.getLength();
                        currentTokens = res.getTokens();
                        previousPassClass = pass.getClass();
                    }

                    break;
                }
            }

            if (!found)
            {
                PlyeApi.error("Error parsing\n");
            }
        }

        return Collections.emptyList();
    }
}
