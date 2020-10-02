package com.example.demo.dao;

import com.example.demo.model.FeatureFlag;
import org.springframework.stereotype.Repository;

public interface FeatureFlagsDAO {
    public FeatureFlag[] getFeatureFlags();
    public FeatureFlag[] setFeatureFlag(FeatureFlag featureFlag);
}
