package dev.wcs.devops.actuator.scheduler;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class SelfCallingScheduler {

    @Scheduled(fixedRateString = "${healthcheck-period-in-ms}")
    public void callServiceHealthCheckDelegate() throws UnirestException {
        log.info(System.currentTimeMillis() + ": calling external service health check.");
        try {
            Unirest.get("http://localhost:8080/checkHealth").asString().getBody();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error on INTERNAL Service call", ex);
        }
    }
}
