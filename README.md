# Events ArkonData

Services for managing event tickets.

## Installation

### Docker

Use [docker](https://www.docker.com/) for installation and [docker compose](https://docs.docker.com/compose/) commands to run containers.

````
docker compose up -d
````

Use these command for executing a specific service in docker-compose.yml file only if a container is not running (only app services).

````
docker compose up -d --build --force-recreate --no-deps graphqlapp

docker compose up -d --build --force-recreate --no-deps restapp
````

### Development

If you don't want to install by docker, you can use [MySQL](https://dev.mysql.com/downloads/installer/) installation and your preferred IDE, for GraphQL API is into /graphql folder and Restful API in into /rest folder.

Open any project and run in your IDE.

## Usage

When containers or you dev setup is running, you can executing request to these API's

```
# For GraphQL API
http://localhost:8080/graphiql
# For Rest API
http://localhost:8081/api/v1/ticket/
http://localhost:8081/api/v1/event/
```

## Requests

Here can you find a [Postman](https://www.postman.com/) configuration to execute requests in Rest and GraphQL.

Take postman-requests.json and import in your postman app.
