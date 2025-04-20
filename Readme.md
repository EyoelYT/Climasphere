
# Table of Contents

-   [Using the Website](#website-usage)
-   [BACKEND](#backend)
    -   [Setup `applications.properties`](#setup-application-dot-properties)
    -   [Command to build and run backend:](#build-and-run-backend)
    -   [API structure for querying current weather:](#query-current-weather)
        -   [Examples of working &rsquo;current-weather&rsquo; requests to the backend:](#working-weather-requests-backend)
        -   [Examples of &rsquo;current-weather&rsquo; responses from the backend:](#weather-responses-from-backend)
    -   [API structure for querying a pexels video:](#query-pexels-video)
        -   [Examples of working query a &rsquo;pexels-video&rsquo; requests to the backend:](#pexels-video-requests-backend)
        -   [Examples of &rsquo;pexels-video&rsquo; responses from the backend:](#pexels-video-responses-from-backend)
-   [FRONTEND](#frontend)
    -   [Serve the frontend](#serve-frontend)
    -   [Example `env.js`](#example-env-js)
-   [Setting up the Project with Docker](#setup-docker)
    -   [Setup a `.env` file in the root of the project](#setup-root-env-file)
    -   [Run docker commands](#run-docker)



<a id="website-usage"></a>

# Using the Website

Put in city or country name into the prompt. It shows the current weather (the
temperature, humidity, and weather condition).


<a id="backend"></a>

# BACKEND


<a id="setup-application-dot-properties"></a>

## Setup `applications.properties`

Setup `applications.properties` in `Climasphere/backend/src/main/resources/application.properties`

The contents should look something like this:

    spring.application.name=climasphere
    
    server.port=BACKEND-PORT-HERE
    
    weather.api.key=OPEN-WEATHER-MAP-API-KEY-HERE
    weather.api.url=https://api.openweathermap.org/data/2.5/weather
    
    pexels.api.key=PEXELS-API-KEY-HERE
    pexels.videoquery.api.url=https://api.pexels.com/videos/search


<a id="build-and-run-backend"></a>

## Command to build and run backend:

-   sh
    
        cd backend
        ./mvnw clean && ./mvnw spring-boot:run
-   cmd
    
        cd backend
        .\mvnw.cmd clean && .\mvnw.cmd spring-boot:run


<a id="query-current-weather"></a>

## API structure for querying current weather:

    http://{HOST}:{PORT}/api/weather?city={required_param},state={optional_param},country={optional_param}


<a id="working-weather-requests-backend"></a>

### Examples of working &rsquo;current-weather&rsquo; requests to the backend:

-   <http://{HOST}:{PORT}/api/weather?city=London>
-   <http://{HOST}:{PORT}/api/weather?city=Addis%20Ababa>
-   <http://{HOST}:{PORT}/api/weather?city=addis%20ababa>
-   <http://{HOST}:{PORT}/api/weather?city=Addis+Ababa>
-   <http://{HOST}:{PORT}/api/weather?city=Addis%20Ababa&country=ET>
-   <http://{HOST}:{PORT}/api/weather?city=Austin&state=TX>
-   <http://{HOST}:{PORT}/api/weather?city=Austin&state=TX&country=US>
-   <http://{HOST}:{PORT}/api/weather?city=addis%20ababa>
-   <http://{HOST}:{PORT}/api/weather?city=São%20Paulo&country=BR>
-   <http://{HOST}:{PORT}/api/weather?city=Addis+Ababa>


<a id="weather-responses-from-backend"></a>

### Examples of &rsquo;current-weather&rsquo; responses from the backend:

-   If city is found, it returns something like these in the response body:

    {
      "name": "Addis Ababa",
      "main": {
        "temp": 20.49,
        "humidity": 30
      },
      "weather": [
        {
          "description": "few clouds"
        }
      ]
    }

    {
      "name": "São Paulo",
      "main": {
        "temp": 21.16,
        "humidity": 86
      },
      "weather": [
        {
          "description": "broken clouds"
        }
      ]
    }

    {
      "name": "Austin",
      "main": {
        "temp": 5.58,
        "humidity": 63
      },
      "weather": [
        {
          "description": "broken clouds"
        }
      ]
    }

-   If city is not found, it returns this in the response body:
    
        Location not found


<a id="query-pexels-video"></a>

## API structure for querying a pexels video:

    http://{HOST}:{PORT}/api/pexels?query=clouds


<a id="pexels-video-requests-backend"></a>

### Examples of working query a &rsquo;pexels-video&rsquo; requests to the backend:

-   <http://{HOST}:{PORT}/api/pexels?query=Food>
-   <http://{HOST}:{PORT}/api/pexels?query=Cloudy%20Day>
-   <http://{HOST}:{PORT}/api/pexels?query=sunny%20day>
-   <http://{HOST}:{PORT}/api/pexels?query=Addis+Ababa>


<a id="pexels-video-responses-from-backend"></a>

### Examples of &rsquo;pexels-video&rsquo; responses from the backend:

-   If a video is found, it returns something like this in the response body:

    https://videos.pexels.com/video-files/3967272/3967272-uhd_2732_1440_24fps.mp4

-   If a video is not found, it returns this in the response body:
    
        Video not found


<a id="frontend"></a>

# FRONTEND


<a id="serve-frontend"></a>

## Serve the frontend

Create `env.js` and add the relevant environment variables into it. Example shown [here](#example-env-js).

    cd frontend/public/
    touch env.js
    python -m http.server 8000

Then open <http://localhost:8000/> or <http://localhost:8000/index.html> in a
web-browser.


<a id="example-env-js"></a>

## Example `env.js`

    const env = {
      port: '8080',
      host: 'localhost',
    };
    
    export { env };


<a id="setup-docker"></a>

# Setting up the Project with Docker

Install [Docker](https://www.docker.com/).

You don&rsquo;t need to setup neither the `env.js` file for the frontend nor the
`application.properties` file for the backend as docker will automate that for you
using your `.env` file that should be at the root of the project.


<a id="setup-root-env-file"></a>

## Setup a `.env` file in the root of the project

    #!/usr/bin/env bash
    
    export PEXELS_API_KEY=PEXELS-API-KEY-HERE
    export PEXELS_API_URL=https://api.pexels.com/videos/search
    export WEATHER_API_KEY=OPEN-WEATHER-MAP-API-KEY-HERE
    export WEATHER_API_URL=https://api.openweathermap.org/data/2.5/weather
    export BACKEND_PORT=8080
    export BACKEND_HOST=localhost


<a id="run-docker"></a>

## Run docker commands

Go to the root of the project and run the following commands:

    $ docker-compose build
    $ docker-compose up

You will be able to access resources using <http://localhost:8000/> or
<http://localhost:8000/index.html>

