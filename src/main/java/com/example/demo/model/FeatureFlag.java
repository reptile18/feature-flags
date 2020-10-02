package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Model class that represents the JSON Feature Flag object of the form { name: "NAME", value: "BITFLAGS" }
 */
public class FeatureFlag implements Serializable {
    private final String featureName;
    private final int featureValue;

    /**
     * The Feature Flag's name
     * @return
     */
    @JsonProperty("name")
    public String getFeatureName() {
        return featureName;
    }

    /**
     * The Feature Flag's value (as a 5-bit flag)
     * @return
     */
    @JsonProperty("value")
    public int getFeatureValue() {
        return featureValue;
    }

    /**
     * Constructs the Feature Flag's object
     * @param featureName the feature flag's name
     * @param featureValue the feature flag's value
     */
    public FeatureFlag(@JsonProperty("name") String featureName,@JsonProperty("value") int featureValue) {
        this.featureName = featureName;
        this.featureValue = featureValue;
    }
}
