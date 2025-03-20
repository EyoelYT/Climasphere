#!/usr/bin/env sh

# start the spring boot application
exec java -jar app.jar                   \
    --server.port=${BACKEND_PORT}        \
    --weather.api.key=${WEATHER_API_KEY} \
    --weather.api.url=${WEATHER_API_URL}
