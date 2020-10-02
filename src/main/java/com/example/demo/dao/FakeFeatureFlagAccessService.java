package com.example.demo.dao;

import com.example.demo.model.FeatureFlag;
import org.springframework.stereotype.Repository;

/**
 * This class is a dummy service for testing the application without needing the microservice running. Not all features
 * available in the microservice will be available in this fake service.
 */
@Repository("fakeDAO")
public class FakeFeatureFlagAccessService implements FeatureFlagsDAO{
    private FeatureFlag[] featureFlags = new FeatureFlag[3];

    /**
     * Returns a dummy list of feature flags
     * @return
     */
    @Override
    public FeatureFlag[] getFeatureFlags() {
        this.featureFlags[0] = new FeatureFlag("test", 7);
        this.featureFlags[1] = new FeatureFlag("test2", 2);
        this.featureFlags[2] = new FeatureFlag("test3", 11);
        return this.featureFlags;
    }

    /**
     * Sets a feature flag (adding a new feature flag is not currently supported).
     * @param featureFlag The feature flag to add, should be in the JSON format { name: "NAME", value: "BITFLAGS" }
     * @return the full list of feature flags after setting the given feature flag
     */
    @Override
    public FeatureFlag[] setFeatureFlag(FeatureFlag featureFlag) {
        System.out.println("in setFeatureFlag");
        for (int i = 0; i < this.featureFlags.length; i++) {
            if (this.featureFlags[i].getFeatureName().equals(featureFlag.getFeatureName())) {
                this.featureFlags[i] = featureFlag;
            }
        }
        return this.featureFlags;
    }
}
