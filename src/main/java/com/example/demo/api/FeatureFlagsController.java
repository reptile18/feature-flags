package com.example.demo.api;

import com.example.demo.model.FeatureFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FeatureFlagsService;

@RequestMapping("api/v1/featureflags")
@RestController
public class FeatureFlagsController {
    private final FeatureFlagsService ffservice;

    @Autowired
    public FeatureFlagsController(FeatureFlagsService ffservice) {
        this.ffservice = ffservice;
    }

    @GetMapping
    public FeatureFlag[] getFeatureFlags() {
        return this.ffservice.getFeatureFlags();
    }

    @PostMapping
    public FeatureFlag[] setFeatureFlag(@RequestBody FeatureFlag featureFlag) {
        return this.ffservice.setFeatureFlag(featureFlag);
    }
}
