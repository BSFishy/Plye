package com.fishy.provalang.api.ast;

import com.fishy.provalang.api.ast.feature.Feature;
import com.fishy.provalang.api.ast.feature.FeatureNode;
import com.fishy.provalang.api.ast.type.*;
import org.jetbrains.annotations.Contract;

@SuppressWarnings("unused")
public class AstApi
{
    @Contract(value = "null -> false", pure = true)
    public static boolean isNode(AstElement element)
    {
        return element instanceof AstNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isFeature(AstElement element)
    {
        return element instanceof Feature;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlock(AstElement element)
    {
        return element instanceof AstBlock;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlock(AstNode node)
    {
        return node instanceof AstBlock;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlock(FeatureNode node)
    {
        return node instanceof AstBlock;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isStatement(AstElement element)
    {
        return element instanceof AstStatement;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isStatement(AstNode node)
    {
        return node instanceof AstStatement;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isFeatureNode(AstElement element)
    {
        return element instanceof FeatureNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isFeatureNode(AstNode node)
    {
        return node instanceof FeatureNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(AstElement element)
    {
        return element instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(AstNode node)
    {
        return node instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(AstStatement statement)
    {
        return statement instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(AstBlock block)
    {
        return block instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isBlockNode(FeatureNode node)
    {
        return node instanceof BlockNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isContainerNode(AstElement element)
    {
        return element instanceof ContainerNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isContainerNode(AstNode node)
    {
        return node instanceof ContainerNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isContainerNode(AstBlock block)
    {
        return block instanceof ContainerNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isContainerNode(FeatureNode node)
    {
        return node instanceof ContainerNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isExpressionNode(AstElement element)
    {
        return element instanceof ExpressionNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isExpressionNode(AstNode node)
    {
        return node instanceof ExpressionNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isExpressionNode(FeatureNode node)
    {
        return node instanceof ExpressionNode;
    }

    @Contract(value = "null -> false", pure = true)
    public static boolean isExpressionNode(AstStatement statement)
    {
        return statement instanceof ExpressionNode;
    }
}
