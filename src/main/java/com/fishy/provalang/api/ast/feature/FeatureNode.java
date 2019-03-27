package com.fishy.provalang.api.ast.feature;

import com.fishy.provalang.api.ast.AstNode;

import java.util.ArrayList;
import java.util.List;

public abstract class FeatureNode extends AstNode
{
    protected final List<Feature> features = new ArrayList<>();

    public void addFeature(Feature feature)
    {
        features.add(feature);
    }

    public List<Feature> getFeatures()
    {
        return features;
    }
}