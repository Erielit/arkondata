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

[ArkonData.postman_collection.json](https://github.com/user-attachments/files/17589172/ArkonData.postman_collection.json){
	"info": {
		"_postman_id": "541b7066-2f65-4ef3-8b9a-1e070eb42c78",
		"name": "ArkonData",
		"schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
		"_exporter_id": "39406460"
	},
	"item": [
		{
			"name": "graphql",
			"item": [
				{
					"name": "event",
					"item": [
						{
							"name": "GraphqlEvents",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "graphql",
									"graphql": {
										"query": "{\r\n  events {\r\n    id\r\n    createdAt\r\n    end\r\n    maxTickets\r\n    name\r\n    start\r\n    status\r\n  }\r\n}",
										"variables": ""
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/graphql",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"graphql"
									]
								},
								"description": "The HTTP POST request to the specified endpoint is used to send a payload with an undefined request body type.\n\n### Response\n\nThe response for this request is in JSON format with a status code of 200. Below is the JSON schema representing the response data structure:\n\n``` json\n{\n    \"data\": {\n        \"events\": [\n            {\n                \"id\": \"8a959290-ab94-4a44-adb8-e6bcf25261c0\",\n                \"createdAt\": \"2024-10-30T16:29:07\",\n                \"end\": \"2024-10-29\",\n                \"maxTickets\": 2,\n                \"name\": \"Evento 4\",\n                \"start\": \"2024-10-30\",\n                \"status\": true\n            },\n            {\n                \"id\": \"cf067f63-4074-4966-9640-db054bc59a1c\",\n                \"createdAt\": \"2024-10-31T00:59:01\",\n                \"end\": \"2024-11-10\",\n                \"maxTickets\": 2,\n                \"name\": \"New event rest update\",\n                \"start\": \"2024-11-01\",\n                \"status\": true\n            },\n            {\n                \"id\": \"d6a0a455-89cb-4b3d-a26c-9bcdb77dfbbb\",\n                \"createdAt\": \"2024-10-30T16:18:33\",\n                \"end\": \"2024-11-30\",\n                \"maxTickets\": 100,\n                \"name\": \"Event One\",\n                \"start\": \"2024-10-30\",\n                \"status\": true\n            }\n        ]\n    }\n}\n\n ```"
							},
							"response": []
						},
						{
							"name": "GraphqlEvent",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "graphql",
									"graphql": {
										"query": "{\r\n  event(id:\"8a959290-ab94-4a44-adb8-e6bcf25261c0\") {\r\n    id\r\n    createdAt\r\n    end\r\n    maxTickets\r\n    name\r\n    start\r\n    status\r\n  }\r\n}",
										"variables": ""
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/graphql",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"graphql"
									]
								},
								"description": "The HTTP POST request to the specified endpoint is used to send a payload with an undefined request body type.\n\n### Response\n\nThe response for this request is in JSON format with a status code of 200. Below is the JSON schema representing the response data structure:\n\n``` json\n{\n    \"data\": {\n        \"event\": {\n            \"id\": \"8a959290-ab94-4a44-adb8-e6bcf25261c0\",\n            \"createdAt\": \"2024-10-30T16:29:07\",\n            \"end\": \"2024-10-29\",\n            \"maxTickets\": 2,\n            \"name\": \"Evento 4\",\n            \"start\": \"2024-10-30\",\n            \"status\": true\n        }\n    }\n}\n\n ```"
							},
							"response": []
						},
						{
							"name": "GraphqlUpdateEvent",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "graphql",
									"graphql": {
										"query": "mutation MyMutation {\r\n  updateEvent(\r\n    event: {\r\n      name: \"Evento 4\"\r\n      start: \"2024-10-30\"\r\n      end: \"2024-10-29\"\r\n      maxTickets: 10\r\n      createdAt: \"2024-10-30T16:29:07\"\r\n      status: true\r\n      id: \"8a959290-ab94-4a44-adb8-e6bcf25261c0\"\r\n    }\r\n  ) {\r\n    id\r\n    status\r\n    start\r\n    name\r\n    end\r\n    createdAt\r\n    maxTickets\r\n  }\r\n}",
										"variables": ""
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/graphql",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"graphql"
									]
								},
								"description": "The HTTP POST request to the specified endpoint is used to send a payload with an undefined request body type.\n\n### Response\n\nThe response for this request is in JSON format with a status code of 200. Below is the JSON schema representing the response data structure:\n\n``` json\n{\n    \"data\": {\n        \"updateEvent\": {\n            \"id\": \"8a959290-ab94-4a44-adb8-e6bcf25261c0\",\n            \"status\": true,\n            \"start\": \"2024-10-30\",\n            \"name\": \"Evento 4\",\n            \"end\": \"2024-10-29\",\n            \"createdAt\": \"2024-10-30T16:29:07\",\n            \"maxTickets\": 10\n        }\n    }\n}\n\n ```"
							},
							"response": []
						},
						{
							"name": "GraphqlCreateEvent",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "graphql",
									"graphql": {
										"query": "mutation createEvent($event: NewEvent!) {\r\n  createEvent(event: $event) {\r\n    id\r\n    end\r\n    createdAt\r\n    maxTickets\r\n    name\r\n    start\r\n    status\r\n  }\r\n}",
										"variables": "{\r\n  \"event\": {\r\n    \"name\":\"Evento graphql 3\",\r\n    \"start\": \"2024-10-31\",\r\n    \"end\": \"2024-11-10\",\r\n    \"maxTickets\": 30\r\n  }\r\n}"
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/graphql",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"graphql"
									]
								},
								"description": "The HTTP POST request to the specified endpoint is used to send a payload with an undefined request body type.\n\n### Response\n\nThe response for this request is in JSON format with a status code of 200. Below is the JSON schema representing the response data structure:\n\n``` json\n{\n    \"data\": {\n        \"createEvent\": {\n            \"id\": \"1febe0f2-ca81-49c3-946f-1ce9ac42becc\",\n            \"end\": \"2024-11-10\",\n            \"createdAt\": \"2024-10-31T01:28:21.135458\",\n            \"maxTickets\": 30,\n            \"name\": \"Evento graphql 3\",\n            \"start\": \"2024-10-31\",\n            \"status\": true\n        }\n    }\n}\n\n ```"
							},
							"response": []
						}
					]
				},
				{
					"name": "ticket",
					"item": [
						{
							"name": "SellTicket",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "graphql",
									"graphql": {
										"query": "mutation MyMutation {\r\n  sellTicket(id: \"1febe0f2-ca81-49c3-946f-1ce9ac42becc\") {\r\n    status\r\n    isRedeem\r\n    id\r\n    createdAt\r\n    event {\r\n      id\r\n      maxTickets\r\n      name\r\n      start\r\n      end\r\n    }\r\n  }\r\n}",
										"variables": ""
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/graphql",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"graphql"
									]
								},
								"description": "### HTTP POST /api/graphql\n\nThis endpoint allows you to make a GraphQL request to sell a ticket.\n\n#### Request Body\n\n- The request body should be in JSON format.\n    \n\n#### Response\n\n- Status: 200\n- Content-Type: application/json\n    \n\n##### Example Response Body\n\n``` json\n{\n    \"data\": {\n        \"sellTicket\": {\n            \"status\": true,\n            \"isRedeem\": true,\n            \"id\": \"\",\n            \"createdAt\": \"\",\n            \"event\": {\n                \"id\": \"\",\n                \"maxTickets\": 0,\n                \"name\": \"\",\n                \"start\": \"\",\n                \"end\": \"\"\n            }\n        }\n    }\n}\n\n ```"
							},
							"response": []
						},
						{
							"name": "GraphqlTickets",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "graphql",
									"graphql": {
										"query": "query MyQuery {\r\n  ticketsByEvent(id: \"1febe0f2-ca81-49c3-946f-1ce9ac42becc\") {\r\n    createdAt\r\n    id\r\n    isRedeem\r\n    status\r\n    event {\r\n      id\r\n      name\r\n      maxTickets\r\n      end\r\n      createdAt\r\n      start\r\n      status\r\n    }\r\n  }\r\n}",
										"variables": ""
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/graphql",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"graphql"
									]
								},
								"description": "### HTTP POST /api/graphql\n\nThis endpoint allows you to make a GraphQL request to sell a ticket.\n\n#### Request Body\n\n- N/A\n    \n\n#### Response\n\n- Status: 200\n- Content-Type: application/json\n    \n\n##### Example Response Body\n\n``` json\n{\n    \"data\": {\n        \"ticketsByEvent\": [\n            {\n                \"createdAt\": \"2024-10-31T01:28:50\",\n                \"id\": \"0dca32dc-63eb-44b6-a4c3-b8d8e9538861\",\n                \"isRedeem\": true,\n                \"status\": true,\n                \"event\": {\n                    \"id\": \"1febe0f2-ca81-49c3-946f-1ce9ac42becc\",\n                    \"name\": \"Evento graphql 3\",\n                    \"maxTickets\": 30,\n                    \"end\": \"2024-11-10\",\n                    \"createdAt\": \"2024-10-31T01:28:21\",\n                    \"start\": \"2024-10-31\",\n                    \"status\": true\n                }\n            }\n        ]\n    }\n}\n\n ```"
							},
							"response": []
						},
						{
							"name": "RedeemTicket",
							"request": {
								"method": "POST",
								"header": [],
								"body": {
									"mode": "graphql",
									"graphql": {
										"query": "mutation MyMutation {\r\n  redeemTicket(id: \"0dca32dc-63eb-44b6-a4c3-b8d8e9538861\")\r\n}",
										"variables": ""
									}
								},
								"url": {
									"raw": "http://localhost:8080/api/graphql",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8080",
									"path": [
										"api",
										"graphql"
									]
								},
								"description": "### HTTP POST /api/graphql\n\nThis endpoint allows you to make a GraphQL request to sell a ticket.\n\n#### Request Body\n\n- N/A\n    \n\n#### Response\n\n- Status: 200\n- Content-Type: application/json\n    \n\n##### Example Response Body\n\n``` json\n{\n  \"data\": {\n    \"redeemTicket\": true\n  }\n}\n\n ```"
							},
							"response": []
						}
					]
				}
			],
			"description": "All of the graphql api"
		},
		{
			"name": "rest",
			"item": [
				{
					"name": "event",
					"item": [
						{
							"name": "RestfulEvent",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [
									{
										"key": "Content-Type",
										"value": "application/json",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8081/api/v1/event/",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8081",
									"path": [
										"api",
										"v1",
										"event",
										""
									]
								},
								"description": "### Retrieve Event Details\n\nThis endpoint allows you to retrieve details of an event.\n\n#### Request\n\n- Method: GET\n- URL: `http://localhost:8081/api/v1/event/`\n    \n\n#### Response\n\nUpon a successful request, the server will respond with a status code of 200 and a JSON object in the response body containing the following fields:\n\n- `message` (string): A message related to the response.\n- `httpStatus` (string): The HTTP status of the response.\n- `error` (boolean): Indicates if an error occurred.\n- `data` (object): An object containing the details of the event with the following fields:\n    - `id` (string): The unique identifier of the event.\n    - `name` (string): The name of the event.\n    - `start` (string): The start date and time of the event.\n    - `end` (string): The end date and time of the event.\n    - `maxTickets` (number): The maximum number of tickets available for the event.\n    - `createdAt` (string): The date and time when the event was created.\n\n``` json\n{\n    \"message\": \"OK\",\n    \"httpStatus\": \"OK\",\n    \"error\": false,\n    \"data\": [\n        {\n            \"id\": \"1febe0f2-ca81-49c3-946f-1ce9ac42becc\",\n            \"name\": \"Evento graphql 3\",\n            \"start\": \"2024-10-31\",\n            \"end\": \"2024-11-10\",\n            \"maxTickets\": 30,\n            \"createdAt\": \"2024-10-31T01:28:21\",\n            \"status\": true\n        },\n        {\n            \"id\": \"8a959290-ab94-4a44-adb8-e6bcf25261c0\",\n            \"name\": \"Evento 4\",\n            \"start\": \"2024-10-30\",\n            \"end\": \"2024-10-29\",\n            \"maxTickets\": 10,\n            \"createdAt\": \"2024-10-30T16:29:07\",\n            \"status\": true\n        }\n    ]\n}\n\n ```"
							},
							"response": []
						},
						{
							"name": "RestfulEventCreate",
							"request": {
								"method": "POST",
								"header": [
									{
										"key": "Content-Type",
										"value": "application/json",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "{\r\n    \"name\": \"New event rest\",\r\n    \"start\": \"2024-11-01\",\r\n    \"end\": \"2024-11-15\",\r\n    \"maxTickets\": 10\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8081/api/v1/event/",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8081",
									"path": [
										"api",
										"v1",
										"event",
										""
									]
								},
								"description": "# Add Event\n\nThis endpoint allows you to add a new event.\n\n## Request Body\n\n- `name` (string): The name of the event.\n- `start` (string): The start date of the event in the format 'YYYY-MM-DD'.\n- `end` (string): The end date of the event in the format 'YYYY-MM-DD'.\n- `maxTickets` (number): The maximum number of tickets available for the event.\n    \n\nExample:\n\n``` json\n{\n    \"name\": \"New event rest\",\n    \"start\": \"2024-11-01\",\n    \"end\": \"2024-11-15\",\n    \"maxTickets\": 10\n}\n\n ```\n\n## Response\n\n``` json\n{\n    \"message\": \"OK\",\n    \"httpStatus\": \"OK\",\n    \"error\": false,\n    \"data\": {\n        \"id\": \"f7f8f69a-3acf-42e5-bad7-cd5c6600c14d\",\n        \"name\": \"New event rest\",\n        \"start\": \"2024-11-01\",\n        \"end\": \"2024-11-15\",\n        \"maxTickets\": 10,\n        \"createdAt\": \"2024-10-31T01:40:41.56564\",\n        \"status\": true\n    }\n}\n\n ```"
							},
							"response": []
						},
						{
							"name": "RestfulEventUpdate",
							"request": {
								"method": "PUT",
								"header": [
									{
										"key": "Content-Type",
										"value": "application/json",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "{\r\n    \"id\": \"cf067f63-4074-4966-9640-db054bc59a1c\",\r\n    \"name\": \"New event rest update\",\r\n    \"start\": \"2024-11-01\",\r\n    \"end\": \"2024-11-10\",\r\n    \"maxTickets\": 2,\r\n    \"createdAt\": \"2024-10-31T00:59:00.809589\",\r\n    \"status\": true\r\n}",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8081/api/v1/event/",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8081",
									"path": [
										"api",
										"v1",
										"event",
										""
									]
								},
								"description": "### Update Event\n\nThis endpoint allows the client to update an existing event by sending an HTTP PUT request to the specified URL.\n\n#### Request Body\n\n- `id` (string): The unique identifier of the event.\n- `name` (string): The updated name of the event.\n- `start` (string): The updated start date of the event.\n- `end` (string): The updated end date of the event.\n- `maxTickets` (integer): The updated maximum number of tickets available for the event.\n- `createdAt` (string): The timestamp when the event was initially created.\n- `status` (boolean): The updated status of the event.\n    \n\n#### Response\n\n- Status: 200\n    \n- Content-Type: application/json\n- `message` (string): A message related to the error.\n- `httpStatus` (string): The HTTP status of the response.\n- `error` (boolean): Indicates if an error occurred.\n- `data` (object): The data related to the response, which may be null in case of an error.\n    \n\n``` json\n{\n    \"message\": \"OK\",\n    \"httpStatus\": \"OK\",\n    \"error\": false,\n    \"data\": {\n        \"id\": \"cf067f63-4074-4966-9640-db054bc59a1c\",\n        \"name\": \"New event rest update\",\n        \"start\": \"2024-11-01\",\n        \"end\": \"2024-11-10\",\n        \"maxTickets\": 2,\n        \"createdAt\": \"2024-10-31T00:59:00.809589\",\n        \"status\": true\n    }\n}\n\n ```"
							},
							"response": []
						}
					]
				},
				{
					"name": "ticket",
					"item": [
						{
							"name": "RestfulTicket",
							"protocolProfileBehavior": {
								"disableBodyPruning": true
							},
							"request": {
								"method": "GET",
								"header": [
									{
										"key": "Content-Type",
										"value": "application/json",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8081/api/v1/ticket/event/cf067f63-4074-4966-9640-db054bc59a1c",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8081",
									"path": [
										"api",
										"v1",
										"ticket",
										"event",
										"cf067f63-4074-4966-9640-db054bc59a1c"
									]
								},
								"description": "### GET /api/v1/ticket/event/{eventId}\n\nThis endpoint retrieves ticket event details based on the provided event ID.\n\n#### Request\n\nNo request body parameters are required for this request.\n\n- Path Parameters\n    - eventId (string, required): The unique identifier for the event.\n\n#### Response\n\nThe response will be in JSON format and follows the schema below:\n\n``` json\n{\n    \"message\": \"OK\",\n    \"httpStatus\": \"OK\",\n    \"error\": false,\n    \"data\": [\n        {\n            \"id\": \"1f7f6a9f-dfd0-48eb-ad4d-4bd737c75be3\",\n            \"isRedeem\": true,\n            \"createdAt\": \"2024-10-31T01:09:34\",\n            \"status\": true,\n            \"event\": {\n                \"id\": \"cf067f63-4074-4966-9640-db054bc59a1c\",\n                \"name\": \"New event rest update\",\n                \"start\": \"2024-11-01\",\n                \"end\": \"2024-11-10\",\n                \"maxTickets\": 2,\n                \"createdAt\": \"2024-10-31T00:59:01\",\n                \"status\": true\n            }\n        },\n        {\n            \"id\": \"4e82630f-f09e-4e3f-9ecb-be60fd07445e\",\n            \"isRedeem\": false,\n            \"createdAt\": \"2024-10-31T01:09:21\",\n            \"status\": true,\n            \"event\": {\n                \"id\": \"cf067f63-4074-4966-9640-db054bc59a1c\",\n                \"name\": \"New event rest update\",\n                \"start\": \"2024-11-01\",\n                \"end\": \"2024-11-10\",\n                \"maxTickets\": 2,\n                \"createdAt\": \"2024-10-31T00:59:01\",\n                \"status\": true\n            }\n        }\n    ]\n}\n\n ```"
							},
							"response": []
						},
						{
							"name": "CreateTicket",
							"request": {
								"method": "POST",
								"header": [
									{
										"key": "Content-Type",
										"value": "application/json",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8081/api/v1/ticket/1febe0f2-ca81-49c3-946f-1ce9ac42becc",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8081",
									"path": [
										"api",
										"v1",
										"ticket",
										"1febe0f2-ca81-49c3-946f-1ce9ac42becc"
									]
								},
								"description": "### Create Ticket\n\nThis endpoint allows the user to create a new ticket by making an HTTP POST request to the specified URL.\n\n#### Request Parameters\n\n- `id` (id): The ID of a event to sell a ticket.\n    \n\n#### Response\n\n``` json\n{\n    \"message\": \"OK\",\n    \"httpStatus\": \"OK\",\n    \"error\": false,\n    \"data\": {\n        \"id\": \"14319382-5e44-4a9c-9988-b89ea641f2ea\",\n        \"isRedeem\": false,\n        \"createdAt\": \"2024-10-31T01:43:38.397689\",\n        \"status\": true,\n        \"event\": {\n            \"id\": \"1febe0f2-ca81-49c3-946f-1ce9ac42becc\",\n            \"name\": \"Evento graphql 3\",\n            \"start\": \"2024-10-31\",\n            \"end\": \"2024-11-10\",\n            \"maxTickets\": 30,\n            \"createdAt\": \"2024-10-31T01:28:21\",\n            \"status\": true\n        }\n    }\n}\n\n ```"
							},
							"response": []
						},
						{
							"name": "RedeemTicket",
							"request": {
								"method": "PATCH",
								"header": [
									{
										"key": "Content-Type",
										"value": "application/json",
										"type": "text"
									}
								],
								"body": {
									"mode": "raw",
									"raw": "",
									"options": {
										"raw": {
											"language": "json"
										}
									}
								},
								"url": {
									"raw": "http://localhost:8081/api/v1/ticket/14319382-5e44-4a9c-9988-b89ea641f2ea",
									"protocol": "http",
									"host": [
										"localhost"
									],
									"port": "8081",
									"path": [
										"api",
										"v1",
										"ticket",
										"14319382-5e44-4a9c-9988-b89ea641f2ea"
									]
								},
								"description": "### Update Ticket Details\n\nThis endpoint is used to redeem a specific ticket.\n\n#### Request Parameters\n\n- `id` (id): The updated redeem of the ticket.\n    \n\n#### Response\n\nIn case of a failed request, the response will include a JSON object with the following fields:\n\n- `message`: A message indicating the reason for the failure.\n- `httpStatus`: The HTTP status code of the response.\n- `error`: A boolean indicating if an error occurred.\n- `data`: A boolean indicating if any data was processed.\n    \n\n``` json\n{\n    \"message\": \"OK\",\n    \"httpStatus\": \"OK\",\n    \"error\": false,\n    \"data\": true\n}\n\n ```"
							},
							"response": []
						}
					]
				}
			],
			"description": "All of restful api"
		}
	]
}
