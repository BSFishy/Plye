package com.fishy.provalang.api.lexer.types;

import com.fishy.provalang.api.lexer.ILexerTokenType;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.LexerCastData;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import com.fishy.provalang.api.lexer.annotation.OverridableLexerTokenType;
import com.fishy.provalang.api.util.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Literal
{
    public static final Integer integer    = new Integer();
    public static final Short   shortToken = new Short();
    public static final Long    longToken  = new Long();
    public static final Byte    byteToken  = new Byte();

    public static final Float  floatToken  = new Float();
    public static final Double doubleToken = new Double();

    public static final Boolean booleanToken = new Boolean();

    public static final String    string    = new String();
    public static final Character character = new Character();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<ILexerTokenType> list)
    {
        LexerApi.addTokenTypes(list, new ILexerTokenType[]{integer, shortToken, longToken, byteToken,
                floatToken, doubleToken,
                booleanToken,
                string, character});
    }

    @Data
    public static class Integer implements ILexerTokenType
    {
        @Override
        public boolean canCast(java.lang.String currentString)
        {
            return currentString.matches("[-]?[0-9]?");
        }

        @Override
        public LexerCastData shouldCast(java.lang.String currentString)
        {
            return new LexerCastData(currentString.matches("[-]?[0-9]+[^0-9\\w]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, java.lang.String currentString)
        {
            return new IntegerData(info, currentString);
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class IntegerData extends LexerTokenData
        {
            public final java.lang.String value;

            public IntegerData(LexerTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @OverridableLexerTokenType(specificOverrides = {Integer.class})
    @Data
    public static class Short implements ILexerTokenType
    {
        @Override
        public boolean canCast(java.lang.String currentString)
        {
            return currentString.matches("[-]?[0-9]?((?<=[0-9])[sS])?");
        }

        @Override
        public LexerCastData shouldCast(java.lang.String currentString)
        {
            return new LexerCastData(currentString.matches("[-]?[0-9]+[sS]?[^0-9\\w]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, java.lang.String currentString)
        {
            return new ShortData(info, currentString);
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class ShortData extends LexerTokenData
        {
            public final java.lang.String value;

            public ShortData(LexerTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @OverridableLexerTokenType(specificOverrides = {Integer.class})
    @Data
    public static class Long implements ILexerTokenType
    {
        @Override
        public boolean canCast(java.lang.String currentString)
        {
            return currentString.matches("[-]?[0-9]?((?<=[0-9])[lL])?");
        }

        @Override
        public LexerCastData shouldCast(java.lang.String currentString)
        {
            return new LexerCastData(currentString.matches("[-]?[0-9]+[lL]?[^0-9\\w]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, java.lang.String currentString)
        {
            return new LongData(info, currentString);
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class LongData extends LexerTokenData
        {
            public final java.lang.String value;

            public LongData(LexerTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @OverridableLexerTokenType(specificOverrides = {Integer.class})
    @Data
    public static class Byte implements ILexerTokenType
    {
        @Override
        public boolean canCast(java.lang.String currentString)
        {
            return currentString.matches("[-]?[0-9]?((?<=[0-9])[bB])?");
        }

        @Override
        public LexerCastData shouldCast(java.lang.String currentString)
        {
            return new LexerCastData(currentString.matches("[-]?[0-9]+[bB]?[^0-9\\w]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, java.lang.String currentString)
        {
            return new ByteData(info, currentString);
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class ByteData extends LexerTokenData
        {
            public final java.lang.String value;

            public ByteData(LexerTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @OverridableLexerTokenType(specificOverrides = {Integer.class})
    @Data
    public static class Float implements ILexerTokenType
    {
        @Override
        public boolean canCast(java.lang.String currentString)
        {
            return currentString.matches("[-]?[0-9]?(\\.[0-9]*)?((?<=[0-9.])[fF])?");
        }

        @Override
        public LexerCastData shouldCast(java.lang.String currentString)
        {
            return new LexerCastData(currentString.matches("[-]?[0-9]+(\\.[0-9]*)?[fF]?[^0-9\\w]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, java.lang.String currentString)
        {
            return new FloatData(info, currentString);
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class FloatData extends LexerTokenData
        {
            public final java.lang.String value;

            public FloatData(LexerTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @OverridableLexerTokenType(specificOverrides = {Float.class})
    @Data
    public static class Double implements ILexerTokenType
    {
        @Override
        public boolean canCast(java.lang.String currentString)
        {
            return currentString.matches("[-]?[0-9]?(\\.[0-9]*)?((?<=[0-9.])[dD])?");
        }

        @Override
        public LexerCastData shouldCast(java.lang.String currentString)
        {
            return new LexerCastData(currentString.matches("[-]?[0-9]+(\\.[0-9]*)?[dD]?[^0-9\\w]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, java.lang.String currentString)
        {
            return new DoubleData(info, currentString);
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class DoubleData extends LexerTokenData
        {
            public final java.lang.String value;

            public DoubleData(LexerTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Boolean implements ILexerTokenType
    {
        @Override
        public boolean canCast(java.lang.String currentString)
        {
            return currentString.matches("(" + Utils.generateLeadingRegex("false") + "|" + Utils.generateLeadingRegex("true") + ")");
        }

        @Override
        public LexerCastData shouldCast(java.lang.String currentString)
        {
            return new LexerCastData(currentString.matches("(true|false)[^\\w]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, java.lang.String currentString)
        {
            return new BooleanData(info, currentString);
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class BooleanData extends LexerTokenData
        {
            public final java.lang.String value;

            public BooleanData(LexerTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class String implements ILexerTokenType
    {
        @Override
        public boolean canCast(java.lang.String currentString)
        {
            return currentString.matches("\"(\\\\\"|[^\"])*[\"]?");
        }

        @Override
        public LexerCastData shouldCast(java.lang.String currentString)
        {
            return new LexerCastData(currentString.matches("\"(\\\\\"|[^\"])*\""), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, java.lang.String currentString)
        {
            return new StringData(info, currentString.substring(1, currentString.length() - 1));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class StringData extends LexerTokenData
        {
            public final java.lang.String value;

            public StringData(LexerTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Character implements ILexerTokenType
    {
        @Override
        public boolean canCast(java.lang.String currentString)
        {
            return currentString.matches("'[^']?");
        }

        @Override
        public LexerCastData shouldCast(java.lang.String currentString)
        {
            return new LexerCastData(currentString.matches("'[^']+'"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, java.lang.String currentString)
        {
            return new CharacterData(info, currentString.substring(1, currentString.length() - 1));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class CharacterData extends LexerTokenData
        {
            public final java.lang.String value;

            public CharacterData(LexerTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }
}
