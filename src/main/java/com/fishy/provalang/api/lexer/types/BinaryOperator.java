package com.fishy.provalang.api.lexer.types;

import com.fishy.provalang.api.lexer.ILexerTokenType;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.LexerCastData;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import com.fishy.provalang.api.lexer.annotation.OverridableLexerTokenType;
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

    public static void addDefaultTypes(List<ILexerTokenType> list)
    {
        LexerApi.addTokenTypes(list, new ILexerTokenType[]{assign, equals, notEquals,
                add, subtract, multiply, divide,
                greaterThan, lessThan, greaterThanEquals, lessThanEquals,
                and, or, xor,
                booleanAnd, booleanOr,
                modulus,
                shiftLeft, shiftRight});
    }

    @OverridableLexerTokenType(specificOverrides = {Equals.class, NotEquals.class})
    @Data
    public static class Assign implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("=");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("=[^!=]?"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.Assign);
        }
    }

    @Data
    public static class Equals implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("=[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("=="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.Equals);
        }
    }

    @Data
    public static class NotEquals implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("![=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("!="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.NotEquals);
        }
    }

    @Data
    public static class Add implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\+");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\+[^=]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.Add);
        }
    }

    @Data
    public static class Subtract implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("-");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("-[^=]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.Subtract);
        }
    }

    @Data
    public static class Multiply implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\*");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\*[^=]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.Multiply);
        }
    }

    @Data
    public static class Divide implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("/");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("/[^=]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.Divide);
        }
    }

    @OverridableLexerTokenType(specificOverrides = {GreaterThanEquals.class, ShiftRight.class})
    @Data
    public static class GreaterThan implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(">");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches(">[^=]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.GreaterThan);
        }
    }

    @OverridableLexerTokenType(specificOverrides = {LessThanEquals.class, ShiftLeft.class})
    @Data
    public static class LessThan implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("<");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("<[^=]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.LessThan);
        }
    }

    @Data
    public static class GreaterThanEquals implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(">[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches(">="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.GreaterThanEquals);
        }
    }

    @Data
    public static class LessThanEquals implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("<[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("<="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.LessThanEquals);
        }
    }

    @OverridableLexerTokenType(specificOverrides = {BooleanAnd.class})
    @Data
    public static class And implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("&");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("&[^&]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.And);
        }
    }

    @OverridableLexerTokenType(specificOverrides = {BooleanOr.class})
    @Data
    public static class Or implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\|");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\|[^|]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.Or);
        }
    }

    @Data
    public static class Xor implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\^");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\^[^\\^]"), true);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.Xor);
        }
    }

    @Data
    public static class BooleanAnd implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("&[&]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("&&"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.BooleanAnd);
        }
    }

    @Data
    public static class BooleanOr implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\|[|]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\|\\|"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.BooleanOr);
        }
    }

    @Data
    public static class Modulus implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("%");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("%"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.Modulus);
        }
    }

    @Data
    public static class ShiftLeft implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("<[<]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("<<"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new BinaryOperatorData(info, OperatorType.ShiftLeft);
        }
    }

    @Data
    public static class ShiftRight implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(">[>]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches(">>"), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
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
    public static class BinaryOperatorData extends ILexerTokenType.LexerTokenData
    {
        public final OperatorType type;

        public BinaryOperatorData(LexerTokenInfo info, OperatorType type)
        {
            super(info);

            this.type = type;
        }
    }
}
