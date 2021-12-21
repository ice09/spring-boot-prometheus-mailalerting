## Spring Boot Actuator Prometheus Alertmanager with Google Mail

This sample service calls an external service URL each `healthcheck-period-in-ms` and logs errors.
The resulting metric `logback-error` is used by _prometheus_ and _alertmanager_ for firing mail notifications with Google Mail.
The configuration is included in the `docker` subfolder.

### Build

* Run `mvn spring-boot:build-image`

### Deploy

* `cd docker`
* Change mail credentials in `alertmanager.yml`
* Change `EXTERNAL_SERVICE_URL` in `docker-compose.yml`
* Run `docker-compose up -d`

### Test

* Open http://localhost:9090
