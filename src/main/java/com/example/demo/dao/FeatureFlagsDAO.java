package com.example.demo.dao;

import com.example.demo.model.FeatureFlag;
import org.springframework.stereotype.Repository;

/**
 * The interface for services the FeatureFlagsController will use for the application
 */
public interface FeatureFlagsDAO {
    /**
     * Returns the current list of feature flags
     * @return the current list of feature flags
     */
    public FeatureFlag[] getFeatureFlags();

    /**
     * Sets the feature flag
     * @param featureFlag the feature flag to set
     * @return the current list of feature flags after the set
     */
    public FeatureFlag[] setFeatureFlag(FeatureFlag featureFlag);
}
