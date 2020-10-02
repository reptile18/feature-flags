package com.example.demo.dao;

import com.example.demo.model.FeatureFlag;
import org.springframework.stereotype.Repository;

@Repository("fakeDAO")
public class FakeFeatureFlagAccessService implements FeatureFlagsDAO{
    private FeatureFlag[] featureFlags = new FeatureFlag[3];

    @Override
    public FeatureFlag[] getFeatureFlags() {
        this.featureFlags[0] = new FeatureFlag("test", 7);
        this.featureFlags[1] = new FeatureFlag("test2", 2);
        this.featureFlags[2] = new FeatureFlag("test3", 11);
        return this.featureFlags;
    }

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
