package com.fishy.provalang.api.ast;

import com.fishy.provalang.api.ast.feature.Feature;
import com.fishy.provalang.api.ast.feature.FeatureNode;
import com.fishy.provalang.api.ast.type.*;

@SuppressWarnings("unused")
public class AstApi
{
    public static boolean isNode(AstElement element)
    {
        return element instanceof AstNode;
    }

    public static boolean isFeature(AstElement element)
    {
        return element instanceof Feature;
    }

    public static boolean isBlock(AstElement element)
    {
        return element instanceof AstBlock;
    }

    public static boolean isBlock(AstNode node)
    {
        return node instanceof AstBlock;
    }

    public static boolean isBlock(FeatureNode node)
    {
        return node instanceof AstBlock;
    }

    public static boolean isStatement(AstElement element)
    {
        return element instanceof AstStatement;
    }

    public static boolean isStatement(AstNode node)
    {
        return node instanceof AstStatement;
    }

    public static boolean isFeatureNode(AstElement element)
    {
        return element instanceof FeatureNode;
    }

    public static boolean isFeatureNode(AstNode node)
    {
        return node instanceof FeatureNode;
    }

    public static boolean isBlockNode(AstElement element)
    {
        return element instanceof BlockNode;
    }

    public static boolean isBlockNode(AstNode node)
    {
        return node instanceof BlockNode;
    }

    public static boolean isBlockNode(AstStatement statement)
    {
        return statement instanceof BlockNode;
    }

    public static boolean isBlockNode(AstBlock block)
    {
        return block instanceof BlockNode;
    }

    public static boolean isBlockNode(FeatureNode node)
    {
        return node instanceof BlockNode;
    }

    public static boolean isContainerNode(AstElement element)
    {
        return element instanceof ContainerNode;
    }

    public static boolean isContainerNode(AstNode node)
    {
        return node instanceof ContainerNode;
    }

    public static boolean isContainerNode(AstBlock block)
    {
        return block instanceof ContainerNode;
    }

    public static boolean isContainerNode(FeatureNode node)
    {
        return node instanceof ContainerNode;
    }

    public static boolean isExpressionNode(AstElement element)
    {
        return element instanceof ExpressionNode;
    }

    public static boolean isExpressionNode(AstNode node)
    {
        return node instanceof ExpressionNode;
    }

    public static boolean isExpressionNode(FeatureNode node)
    {
        return node instanceof ExpressionNode;
    }

    public static boolean isExpressionNode(AstStatement statement)
    {
        return statement instanceof ExpressionNode;
    }
}
