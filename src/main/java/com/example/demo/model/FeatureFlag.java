package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class FeatureFlag implements Serializable {
    private final String featureName;

    @JsonProperty("name")
    public String getFeatureName() {
        return featureName;
    }

    @JsonProperty("value")
    public int getFeatureValue() {
        return featureValue;
    }

    private final int featureValue;

    public FeatureFlag(@JsonProperty("name") String featureName,@JsonProperty("value") int featureValue) {
        this.featureName = featureName;
        this.featureValue = featureValue;
    }
}
