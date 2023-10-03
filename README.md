# Meteorological Challenge
This is a challenge made as a technical test. This repo use Kotlin, Ktor and Redis.

## Requirements

### Redis Stack:

-  Mac:
```
brew tap redis-stack/redis-stack
brew install redis-stack
redis-stack-server
```

- Windows: click [here](https://redis.io/docs/getting-started/install-stack/windows/) to see docs.

- Linux: click [here](https://redis.io/docs/getting-started/install-stack/linux/) to see docs.

## How to use:

The best approach to run this project is using IntelliJ IDEA.

The server runs on `http://localhost:8080` host, and Redis client runs on `redis://localhost:6379`. Every url is hardcoded (there's no env vars or configuration, sadly).

Once started ApplicationKt the `LocationService` will hit up the WeatherAPI (see docs [here](https://www.weatherapi.com/docs/)) passing a enum property with the name of the specific location (Santiago, Zurich, Auckland, Sydney, London and Georgia). To try get every location stored in the database you can use this endpoint:

`http://localhost:8080/location/{name}`

OpenAPI is available in `documentation.yaml` file, or in the next url:

`http://localhost:8080/openapi`

Every 5 minutes or 300000 milliseconds the service will try to get meteorological data from locations and if everything is ok will store it in database. If a random number is less than 0.2 will raise an error (and save the message with timestamp in database) and the service will retry it again one more time.
