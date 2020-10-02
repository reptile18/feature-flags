package com.example.demo.service;
import com.example.demo.dao.FeatureFlagsDAO;
import com.example.demo.model.FeatureFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.stereotype.Service;


@Service
public class FeatureFlagsService {
//    @Value("${ffservice.host}")
//    private String ffhost;
//    private final RestTemplate restTemplate;
//
//    public FeatureFlagsService(RestTemplateBuilder restTemplateBuilder) {
//        this.restTemplate = restTemplateBuilder.build();
//    }
//
//    public FeatureFlag[] getFeatureFlags() {
//        return this.restTemplate.getForObject(ffhost, FeatureFlag[].class);
//    }
    private final FeatureFlagsDAO featureFlagDAO;

    @Autowired
    public FeatureFlagsService(@Qualifier("restDAO") FeatureFlagsDAO featureFlagsDAO) {
        this.featureFlagDAO = featureFlagsDAO;
    }

    public FeatureFlag[] getFeatureFlags() {
        return this.featureFlagDAO.getFeatureFlags();
    }

    public FeatureFlag[] setFeatureFlag(FeatureFlag featureFlag) {
        return this.featureFlagDAO.setFeatureFlag(featureFlag);
    }




}
