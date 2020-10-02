package com.example.demo.model;
import com.example.demo.model.FeatureFlag;
import java.io.Serializable;

public class FeatureFlags implements Serializable {
    private FeatureFlag[] featureFlags;

    public FeatureFlags(FeatureFlag[] featureFlags) {
        this.featureFlags = featureFlags;
    }
}
