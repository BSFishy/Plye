package com.fishy.provalang.api.lexer.types;

import com.fishy.provalang.api.lexer.ILexerTokenType;
import com.fishy.provalang.api.lexer.LexerApi;
import com.fishy.provalang.api.lexer.LexerCastData;
import com.fishy.provalang.api.lexer.LexerTokenInfo;
import com.fishy.provalang.api.lexer.annotation.OverridableLexerTokenType;
import com.fishy.provalang.api.lexer.annotation.OverridableLexerTokenType.Priority;
import com.fishy.provalang.api.util.Utils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class OpAssignOperator
{
    public static final Add      add      = new Add();
    public static final Subtract subtract = new Subtract();
    public static final Multiply multiply = new Multiply();
    public static final Divide   divide   = new Divide();

    public static final ShiftLeft  shiftLeft  = new ShiftLeft();
    public static final ShiftRight shiftRight = new ShiftRight();

    public static final And and = new And();
    public static final Or  or  = new Or();
    public static final Xor xor = new Xor();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<ILexerTokenType> list)
    {
        LexerApi.addTokenTypes(list, new ILexerTokenType[]{add, subtract, multiply, divide,
                and, or, xor,
                shiftLeft, shiftRight});
    }

    @Data
    @OverridableLexerTokenType(priority = Priority.Low)
    public static class Add implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\+[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\+="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new OpAssignOperatorData(info, OperatorType.Add);
        }
    }

    @Data
    @OverridableLexerTokenType(priority = Priority.Low)
    public static class Subtract implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("-[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("-="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new OpAssignOperatorData(info, OperatorType.Subtract);
        }
    }

    @Data
    @OverridableLexerTokenType(priority = Priority.Low)
    public static class Multiply implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\*[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\*="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new OpAssignOperatorData(info, OperatorType.Multiply);
        }
    }

    @Data
    @OverridableLexerTokenType(priority = Priority.Low)
    public static class Divide implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("/[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("/="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new OpAssignOperatorData(info, OperatorType.Divide);
        }
    }

    @Data
    @OverridableLexerTokenType(priority = Priority.Low)
    public static class ShiftLeft implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex("<<="));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("<<="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new OpAssignOperatorData(info, OperatorType.ShiftLeft);
        }
    }

    @Data
    @OverridableLexerTokenType(priority = Priority.Low)
    public static class ShiftRight implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches(Utils.generateLeadingRegex(">>="));
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches(">>="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new OpAssignOperatorData(info, OperatorType.ShiftRight);
        }
    }

    @Data
    @OverridableLexerTokenType(priority = Priority.Low)
    public static class And implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("&[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("&="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new OpAssignOperatorData(info, OperatorType.And);
        }
    }

    @Data
    @OverridableLexerTokenType(priority = Priority.Low)
    public static class Or implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\|[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\|="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new OpAssignOperatorData(info, OperatorType.Or);
        }
    }

    @Data
    @OverridableLexerTokenType(priority = Priority.Low)
    public static class Xor implements ILexerTokenType
    {
        @Override
        public boolean canCast(String currentString)
        {
            return currentString.matches("\\^[=]?");
        }

        @Override
        public LexerCastData shouldCast(String currentString)
        {
            return new LexerCastData(currentString.matches("\\^="), false);
        }

        @Override
        public LexerTokenData cast(LexerTokenInfo info, String currentString)
        {
            return new OpAssignOperatorData(info, OperatorType.Xor);
        }
    }

    public enum OperatorType
    {
        Add,
        Subtract,
        Multiply,
        Divide,

        ShiftLeft,
        ShiftRight,

        And,
        Or,
        Xor
    }

    @EqualsAndHashCode(callSuper = false)
    @Data
    public static class OpAssignOperatorData extends ILexerTokenType.LexerTokenData
    {
        public final OperatorType type;

        public OpAssignOperatorData(LexerTokenInfo info, OperatorType type)
        {
            super(info);

            this.type = type;
        }
    }
}
