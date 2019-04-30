////////////////////////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019 Matt Provost                                                                 /
////////////////////////////////////////////////////////////////////////////////////////////////////

package com.fishy.plye.api.ast;

import com.fishy.plye.api.ast.feature.Feature;
import com.fishy.plye.api.ast.feature.FeatureNode;
import com.fishy.plye.api.ast.type.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public class AstApi
{
    @Contract(value = "null -> false", pure = true)
    public static boolean isNode(@Nullable AstElement element)
    {
        return element instanceof AstNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isFeature(@Nullable AstElement element)
    {
        return element instanceof Feature;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlock(@Nullable AstElement element)
    {
        return element instanceof AstBlock;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlock(@Nullable AstNode node)
    {
        return node instanceof AstBlock;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlock(@Nullable FeatureNode node)
    {
        return node instanceof AstBlock;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isStatement(@Nullable AstElement element)
    {
        return element instanceof AstStatement;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isStatement(@Nullable AstNode node)
    {
        return node instanceof AstStatement;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isFeatureNode(@Nullable AstElement element)
    {
        return element instanceof FeatureNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isFeatureNode(@Nullable AstNode node)
    {
        return node instanceof FeatureNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(@Nullable AstElement element)
    {
        return element instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(@Nullable AstNode node)
    {
        return node instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(@Nullable AstStatement statement)
    {
        return statement instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(@Nullable AstBlock block)
    {
        return block instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(@Nullable FeatureNode node)
    {
        return node instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isContainerNode(@Nullable AstElement element)
    {
        return element instanceof ContainerNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isContainerNode(@Nullable AstNode node)
    {
        return node instanceof ContainerNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isContainerNode(@Nullable AstBlock block)
    {
        return block instanceof ContainerNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isContainerNode(@Nullable FeatureNode node)
    {
        return node instanceof ContainerNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isExpressionNode(@Nullable AstElement element)
    {
        return element instanceof ExpressionNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isExpressionNode(@Nullable AstNode node)
    {
        return node instanceof ExpressionNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isExpressionNode(@Nullable FeatureNode node)
    {
        return node instanceof ExpressionNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isExpressionNode(@Nullable AstStatement statement)
    {
        return statement instanceof ExpressionNode;
    }
}
