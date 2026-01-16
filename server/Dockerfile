# ---------- Build stage ----------
FROM eclipse-temurin:11-jdk-alpine AS build

WORKDIR /app

# Copy everything and build
COPY . .
RUN chmod +x mvnw \
    && ./mvnw -B clean package -DskipTests


# ---------- Runtime stage ----------
FROM eclipse-temurin:11-jre-alpine

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# JVM container-aware settings
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
