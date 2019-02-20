package com.fishy.provalang.lexer.tokens;

import com.fishy.provalang.api.lexer.LexerTokenInfo;
import com.fishy.provalang.api.lexerNew.LexerApi;
import com.fishy.provalang.api.lexerNew.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class BinaryOperator
{
    public static final Assign    assign    = new Assign();
    public static final Equals    equals    = new Equals();
    public static final NotEquals notEquals = new NotEquals();

    public static final Add      add      = new Add();
    public static final Subtract subtract = new Subtract();
    public static final Multiply multiply = new Multiply();
    public static final Divide   divide   = new Divide();

    public static final GreaterThan       greaterThan       = new GreaterThan();
    public static final LessThan          lessThan          = new LessThan();
    public static final GreaterThanEquals greaterThanEquals = new GreaterThanEquals();
    public static final LessThanEquals    lessThanEquals    = new LessThanEquals();

    public static final And and = new And();
    public static final Or  or  = new Or();
    public static final Xor xor = new Xor();

    public static final BooleanAnd booleanAnd = new BooleanAnd();
    public static final BooleanOr  booleanOr  = new BooleanOr();

    public static final Modulus modulus = new Modulus();

    public static final ShiftLeft  shiftLeft  = new ShiftLeft();
    public static final ShiftRight shiftRight = new ShiftRight();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<TokenType> tokens)
    {
        LexerApi.addTokenTypes(tokens, new TokenType[]{
                assign, equals, notEquals,
                add, subtract, multiply, divide,
                greaterThan, lessThan, greaterThanEquals, lessThanEquals,
                and, or, xor,
                booleanAnd, booleanOr,
                modulus,
                shiftLeft, shiftRight
        });
    }

    @Data
    public static class Assign implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.Assign);
        }
    }

    @Data
    public static class Equals implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.Equals);
        }
    }

    @Data
    public static class NotEquals implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.NotEquals);
        }
    }

    @Data
    public static class Add implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.Add);
        }
    }

    @Data
    public static class Subtract implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.Subtract);
        }
    }

    @Data
    public static class Multiply implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.Multiply);
        }
    }

    @Data
    public static class Divide implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.Divide);
        }
    }

    @Data
    public static class GreaterThan implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.GreaterThan);
        }
    }

    @Data
    public static class LessThan implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.LessThan);
        }
    }

    @Data
    public static class GreaterThanEquals implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.GreaterThanEquals);
        }
    }

    @Data
    public static class LessThanEquals implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.LessThanEquals);
        }
    }

    @Data
    public static class And implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.And);
        }
    }

    @Data
    public static class Or implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.Or);
        }
    }

    @Data
    public static class Xor implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.Xor);
        }
    }

    @Data
    public static class BooleanAnd implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.BooleanAnd);
        }
    }

    @Data
    public static class BooleanOr implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.BooleanOr);
        }
    }

    @Data
    public static class Modulus implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.Modulus);
        }
    }

    @Data
    public static class ShiftLeft implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.ShiftLeft);
        }
    }

    @Data
    public static class ShiftRight implements TokenType
    {

        @Override
        public TokenData cast(LexerTokenInfo info, char[] buffer)
        {
            return new BinaryOperatorData(info, OperatorType.ShiftRight);
        }
    }

    public enum OperatorType
    {
        Assign,
        Equals,
        NotEquals,

        Add,
        Subtract,
        Multiply,
        Divide,

        GreaterThan,
        LessThan,
        GreaterThanEquals,
        LessThanEquals,

        And,
        Or,
        Xor,

        BooleanAnd,
        BooleanOr,

        Modulus,

        ShiftLeft,
        ShiftRight
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class BinaryOperatorData extends TokenType.TokenData
    {
        public final OperatorType type;

        public BinaryOperatorData(LexerTokenInfo info, OperatorType type)
        {
            super(info);

            this.type = type;
        }
    }
}
