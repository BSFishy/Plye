////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.language;

import com.fishy.plye.api.PlyeApi;
import com.fishy.plye.api.data.parser.PassResult;
import com.fishy.plye.api.language.PassHandler;
import com.fishy.plye.file.ProgramFile;
import com.fishy.plye.parser.pass.containerization.ContainerizationPass;
import com.fishy.plye.parser.pass.containerization.LexerToken;
import com.fishy.plye.parser.pass.containerization.token.ContainerizationToken;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlyePassHandler extends PassHandler
{
    public PlyePassHandler()
    {
        super(PlyeLanguage.NAME);
    }

    private ContainerizationPass containerizationPass = ContainerizationPass.instance;

    @Override
    public void preparePasses()
    {
        containerizationPass.defaultDefiners();
    }

    @Override
    public void passes(@NotNull ProgramFile file)
    {
        PassResult<ContainerizationToken> containerization = containerizationPass.parse(LexerToken.convert(file.getTokens()));

        check(containerization, file);
    }

    private void check(@NotNull PassResult result, @NotNull ProgramFile file) {
        if(!result.isFull()) {
            PlyeApi.error("Did not fully parse %s", file.filename);
        }
    }
}
