package com.fishy.plye.lexer.tokens;

import com.fishy.plye.api.lexer.LexTokenInfo;
import com.fishy.plye.api.lexer.LexerApi;
import com.fishy.plye.api.lexer.TokenType;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

public class Separator
{
    public static final BlockOpen  blockOpen  = new BlockOpen();
    public static final BlockClose blockClose = new BlockClose();

    public static final CollectionOpen  collectionOpen  = new CollectionOpen();
    public static final CollectionClose collectionClose = new CollectionClose();

    public static final GroupOpen  groupOpen  = new GroupOpen();
    public static final GroupClose groupClose = new GroupClose();

    public static final Semicolon semicolon = new Semicolon();
    public static final Period    period    = new Period();

    public static void addDefaultTypes()
    {
        addDefaultTypes(LexerApi.getTokens());
    }

    public static void addDefaultTypes(List<TokenType> list)
    {
        LexerApi.addTokenTypes(list, new TokenType[]{
                blockOpen, blockClose,
                collectionOpen, collectionClose,
                groupOpen, groupClose,
                semicolon, period
        });
    }

    public enum SeparatorType
    {
        BlockOpen,
        BlockClose,

        CollectionOpen,
        CollectionClose,

        GroupOpen,
        GroupClose,

        Semicolon,
        Period
    }

    public interface SeparatorTokenType extends TokenType {}

    @Data
    public static class BlockOpen implements SeparatorTokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.BlockOpen);
        }
    }

    @Data
    public static class BlockClose implements SeparatorTokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.BlockClose);
        }
    }

    @Data
    public static class CollectionOpen implements SeparatorTokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.CollectionOpen);
        }
    }

    @Data
    public static class CollectionClose implements SeparatorTokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.CollectionClose);
        }
    }

    @Data
    public static class GroupOpen implements SeparatorTokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.GroupOpen);
        }
    }

    @Data
    public static class GroupClose implements SeparatorTokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.GroupClose);
        }
    }

    @Data
    public static class Semicolon implements SeparatorTokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.Semicolon);
        }
    }

    @Data
    public static class Period implements SeparatorTokenType
    {
        @Override
        public TokenData cast(LexTokenInfo info, char[] buffer)
        {
            return new SeparatorData(info, SeparatorType.Period);
        }
    }

    @EqualsAndHashCode(callSuper = true)
    @Data
    public static class SeparatorData extends TokenType.TokenData
    {

        public final SeparatorType type;

        public SeparatorData(LexTokenInfo info, SeparatorType type)
        {
            super(info);

            this.type = type;
        }
    }
}
