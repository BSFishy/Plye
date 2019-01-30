package com.fishy.provalang.api.lexer;

import com.fishy.provalang.api.lexer.types.Ignored;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public abstract class AbstractLexer implements ILexer
{
    private boolean prepared = false;

    @Getter
    private List<LexerToken> tokens = new ArrayList<>();
    private String           code;
    private String[]         split;

    private int index = 0;

    private LexerTokenInfo info = new LexerTokenInfo();

    protected void prepareCode(String code)
    {
        this.code = code;
        this.split = code.split("");

        LexerApi.addDefaultTypes();

        this.prepared = true;
    }

    protected boolean isPrepared()
    {
        return prepared;
    }

    protected void checkPrepared()
    {
        if (!isPrepared()) throw new IllegalStateException("prepareCode() must be called before any other method");
    }

    protected LexerToken step()
    {
        checkPrepared();

        if (index >= split.length) return new LexerToken(null, new NullTokenData(info.clone()));

        LexerData     tmpData   = null;
        StringBuilder tmpString = new StringBuilder();

        while (true)
        {
            if (!canStep())
            {
                info.decrement();
                return new LexerToken(null, new NullTokenData(info.clone()));
            }

            String character = split[index];
            tmpString.append(character);


            if (tmpData == null)
            {
                tmpData = LexerApi.canCast(tmpString.toString());
            }

            tmpData.canCastElimination(tmpString.toString());

            if(tmpData.empty())
            {
                return new LexerToken(null, new NullTokenData(info.clone()));
            }

            LexerData.LexerShouldCastData data = LexerApi.shouldCast(tmpData, tmpString.toString());

            if (data != null)
            {
                if (!data.data.isLookAhead())
                {
                    info.increment();
                    index++;
                }
                else
                {
                    tmpString.deleteCharAt(tmpString.length()-1);
                }

                if (data.token instanceof Ignored.CarriageReturn || data.token instanceof Ignored.Return)
                    info.incrementLine();

                info.incrementColumn();

                return new LexerToken(data.token, data.token.cast(info.clone(), tmpString.toString()));
            }

            info.increment();
            index++;
        }
    }

    protected boolean canStep()
    {
        return index < split.length;
    }

    @EqualsAndHashCode(callSuper = false)
    @Data
    public static class NullTokenData extends ILexerTokenType.LexerTokenData
    {
        public NullTokenData(LexerTokenInfo info)
        {
            super(info);
        }
    }
}
