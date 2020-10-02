package com.example.demo.api;

import com.example.demo.model.FeatureFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.FeatureFlagsService;

/**
 * Controller for the application, receives HTTP requests and forwards to services implementing the FeatureFlagDAO
 */
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
@RequestMapping("api/v1/featureflags")
@RestController
public class FeatureFlagsController {
    private final FeatureFlagsService ffservice;

    /**
     * Constructs the FeatureFlagsController with the given FeatureFlagsService
     * @param ffservice The FeatureFlagsService the controller will be associated with
     */
    @Autowired
    public FeatureFlagsController(FeatureFlagsService ffservice) {
        this.ffservice = ffservice;
    }

    // I'm sending the flags as-is to the client to save on space, I could break up the bits into booleans, but
    // this would be costly in terms of space

    /**
     * Returns to the client the current list of feature flags and their values
     * @return the current list of feature flags and their values
     */
    @GetMapping
    public FeatureFlag[] getFeatureFlags() {
        return this.ffservice.getFeatureFlags();
    }

    /**
     * Sets a feature flag's value or adds a feature flag to the microservice if it did not previously exist
     * @param featureFlag the feature flag to set
     * @return the current list of feature flags and their values after the set
     */
    @PostMapping
    public FeatureFlag[] setFeatureFlag(@RequestBody FeatureFlag featureFlag) {
        return this.ffservice.setFeatureFlag(featureFlag);
    }
}
