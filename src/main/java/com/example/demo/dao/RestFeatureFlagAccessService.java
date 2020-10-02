package com.example.demo.dao;
import com.example.demo.exception.MicroserviceException;
import com.example.demo.exception.MicroserviceExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import com.example.demo.model.FeatureFlag;
import org.springframework.stereotype.Repository;

/**
 * The service that actually communicates with the feature flag microservice (https://github.com/kmisaal/FFService)
 */
@Repository("restDAO")
public class RestFeatureFlagAccessService implements FeatureFlagsDAO {
    @Value("${ffservice.host}")
    private String ffhost;
    private final RestTemplate restTemplate;

    /**
     * Initializes the RestFeatureFlagAccessService with a {@link RestTemplate} for
     * making HTTP requests to the microservice
     */
    public RestFeatureFlagAccessService() {
        this.restTemplate = new RestTemplate();
        this.restTemplate.setErrorHandler(new MicroserviceExceptionHandler());
    }

    /**
     * Returns the current list of feature flags from the microservice.
     * @return the current list of feature flags
     * @throws {@link MicroserviceException} If the microservice connection fails
     */
    @Override
    public FeatureFlag[] getFeatureFlags() {
        try {
            return this.restTemplate.getForObject(ffhost, FeatureFlag[].class);
        }
        catch (ResourceAccessException e) { // need to catch error when we're not able to connect to the microservice
            throw new MicroserviceException(HttpStatus.NOT_FOUND, "Could not connect to Feature Flag Microservice");
        }
    }

    /**
     * Sets the feature flag onto the microservice.
     * @param featureFlag the feature flag to set
     * @return the current list of feature flags in the microservice after the set
     * @throws {@link MicroserviceException} if the microservice connection fails
     */
    @Override
    public FeatureFlag[] setFeatureFlag(FeatureFlag featureFlag) {
        try {
            return this.restTemplate.postForObject(ffhost, featureFlag, FeatureFlag[].class);
        }
        catch (ResourceAccessException e) { // need to catch error when we're not able to connect to the microservice
            throw new MicroserviceException(HttpStatus.NOT_FOUND, "Could not connect to Feature Flag Microservice");
        }
    }
}
