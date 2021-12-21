package dev.wcs.devops.actuator.controller;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Slf4j
public class HealthCheckController {

    @Value("${healthcheck-url}")
    private String healthCheckUrl;

    @GetMapping("/checkHealth")
    public String checkHealth() {
        try {
            return Unirest.get(healthCheckUrl).asString().getBody();
        } catch (Exception ex) {
            log.error("Error on external Service call: " + ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error on external Service call", ex);
        }
    }
}
