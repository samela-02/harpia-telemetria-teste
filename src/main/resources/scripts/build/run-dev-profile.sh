#!/bin/bash
./mvnw clean package &&
java -jar -Dspring.profiles.active=dev -Dlogging.level.br.tec.bemtevi.harpia_ms_telemetria=debug ./target/harpia-ms-telemetria-0.0.1-SNAPSHOT.jar