package com.fishy.provalang.lexer.tokens;

import com.fishy.provalang.api.lexer.LexerTokenInfo;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Literal
{
    public static final Integer integer = new Integer();
    public static final Byte byteToken = new Byte();
    public static final Short shortToken = new Short();
    public static final Long longToken = new Long();

    public static final Float floatToken = new Float();
    public static final Double doubleToken = new Double();

    public static final Boolean booleanToken = new Boolean();

    public static final String string = new String();
    public static final Character character = new Character();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<TokenType> list)
    {
        LexerApi.addTokenTypes(list, new TokenType[]{integer, shortToken, longToken, byteToken,
                floatToken, doubleToken,
                booleanToken,
                string, character});
    }

    @Data
    public static class Integer implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new IntegerData(info, java.lang.Integer.parseInt(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class IntegerData extends TokenData
        {
            public final int value;

            public IntegerData(LexerTokenInfo info, int value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Byte implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new ByteData(info, java.lang.Byte.parseByte(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class ByteData extends TokenData
        {
            public final byte value;

            public ByteData(LexerTokenInfo info, byte value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Short implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new ShortData(info, java.lang.Short.parseShort(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class ShortData extends TokenData
        {
            public final short value;

            public ShortData(LexerTokenInfo info, short value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Long implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new LongData(info, java.lang.Long.parseLong(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class LongData extends TokenData
        {
            public final long value;

            public LongData(LexerTokenInfo info, long value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Float implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new FloatData(info, java.lang.Float.parseFloat(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class FloatData extends TokenData
        {
            public final float value;

            public FloatData(LexerTokenInfo info, float value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Double implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new DoubleData(info, java.lang.Double.parseDouble(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class DoubleData extends TokenData
        {
            public final double value;

            public DoubleData(LexerTokenInfo info, double value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Boolean implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BooleanData(info, java.lang.Boolean.parseBoolean(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class BooleanData extends TokenData
        {
            public final boolean value;

            public BooleanData(LexerTokenInfo info, boolean value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class String implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new StringData(info, new java.lang.String(buffer));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class StringData extends TokenData
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
    public static class Character implements TokenType
    {
        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new CharacterData(info, buffer[0]);
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class CharacterData extends TokenData
        {
            public final char value;

            public CharacterData(LexerTokenInfo info, char value)
            {
                super(info);

                this.value = value;
            }
        }
    }
}
