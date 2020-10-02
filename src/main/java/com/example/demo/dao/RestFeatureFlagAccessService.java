package com.example.demo.dao;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import com.example.demo.model.FeatureFlag;
import org.springframework.stereotype.Repository;

@Repository("restDAO")
public class RestFeatureFlagAccessService implements FeatureFlagsDAO {
    @Value("${ffservice.host}")
    private String ffhost;
    private final RestTemplate restTemplate;

    public RestFeatureFlagAccessService() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public FeatureFlag[] getFeatureFlags() {
        return this.restTemplate.getForObject(ffhost, FeatureFlag[].class);
    }

    @Override
    public FeatureFlag[] setFeatureFlag(FeatureFlag featureFlag) {
        return this.restTemplate.postForObject(ffhost, featureFlag, FeatureFlag[].class);
    }
}
