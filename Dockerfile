FROM eclipse-temurin:21-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

LABEL authors="Aclecio Cruz"

ENTRYPOINT ["java", "-cp", "app:app/lib/*", "br.com.api.products.ProductsApplication"]