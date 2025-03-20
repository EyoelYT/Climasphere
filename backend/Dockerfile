# Get official OpenJDK image
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

# copy the project files
COPY . .

# build the spring boot app
RUN ./mvnw clean package -DskipTests

# get a minimal jre
FROM eclipse-temurin:17-jre

WORKDIR /app

# copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# copy the entrypoint script to the appr location
COPY entrypoint.sh /app/entrypoint.sh
RUN chmod +x /app/entrypoint.sh

# expose the backend port
EXPOSE 8080

# Run the backend app
ENTRYPOINT ["/app/entrypoint.sh"]
