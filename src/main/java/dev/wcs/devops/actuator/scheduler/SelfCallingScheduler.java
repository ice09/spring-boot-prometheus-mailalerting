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
            int status = Unirest.get("http://localhost:8080/checkHealth").asString().getStatus();
            if (status != 200) {
                throw new IllegalStateException("HTTP Status is not 200.");
            }
        } catch (Exception ex) {
            log.error("Error on external Service call: " + ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error on INTERNAL Service call", ex);
        }
    }
}
