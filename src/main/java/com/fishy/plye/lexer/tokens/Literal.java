package com.fishy.plye.lexer.tokens;

import com.fishy.plye.api.lexer.LexTokenInfo;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Literal
{
    public static final Integer integer    = new Integer();
    public static final Byte    byteToken  = new Byte();
    public static final Short   shortToken = new Short();
    public static final Long    longToken  = new Long();

    public static final Float  floatToken  = new Float();
    public static final Double doubleToken = new Double();

    public static final Boolean booleanToken = new Boolean();

    public static final String    string    = new String();
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

    public interface LiteralType extends TokenType {}

    public interface NumberType extends LiteralType {}

    public interface FloatingPointType extends NumberType {}

    @Data
    public static class Integer implements NumberType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new IntegerData(info, java.lang.Integer.parseInt(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class IntegerData extends TokenData
        {
            public final int value;

            public IntegerData(LexTokenInfo info, int value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Byte implements NumberType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new ByteData(info, java.lang.Byte.parseByte(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class ByteData extends TokenData
        {
            public final byte value;

            public ByteData(LexTokenInfo info, byte value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Short implements NumberType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new ShortData(info, java.lang.Short.parseShort(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class ShortData extends TokenData
        {
            public final short value;

            public ShortData(LexTokenInfo info, short value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Long implements NumberType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new LongData(info, java.lang.Long.parseLong(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class LongData extends TokenData
        {
            public final long value;

            public LongData(LexTokenInfo info, long value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Float implements FloatingPointType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new FloatData(info, java.lang.Float.parseFloat(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class FloatData extends TokenData
        {
            public final float value;

            public FloatData(LexTokenInfo info, float value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Double implements FloatingPointType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new DoubleData(info, java.lang.Double.parseDouble(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class DoubleData extends TokenData
        {
            public final double value;

            public DoubleData(LexTokenInfo info, double value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Boolean implements LiteralType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new BooleanData(info, java.lang.Boolean.parseBoolean(new java.lang.String(buffer)));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class BooleanData extends TokenData
        {
            public final boolean value;

            public BooleanData(LexTokenInfo info, boolean value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class String implements LiteralType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new StringData(info, new java.lang.String(buffer));
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class StringData extends TokenData
        {
            public final java.lang.String value;

            public StringData(LexTokenInfo info, java.lang.String value)
            {
                super(info);

                this.value = value;
            }
        }
    }

    @Data
    public static class Character implements LiteralType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new CharacterData(info, buffer[0]);
        }

        @EqualsAndHashCode(callSuper = false)
        @Data
        public static class CharacterData extends TokenData
        {
            public final char value;

            public CharacterData(LexTokenInfo info, char value)
            {
                super(info);

                this.value = value;
            }
        }
    }
}
