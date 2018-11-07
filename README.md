<h2>Inventory API </h2>
Built using Java 1.8, Spring Boot 2.0.4 with IntelliJ IDEA 

<h3>Steps to setup:</h3>

1. Install Java (jre1.8.0_152), PostgreSQL or Docker.

1. If PostgreSQL not installed, use Docker to setup PostgresSQL container, assume Docker is installed, run `sudo docker-compose up` in the same path as `docker-compose.yml` file located, to initiate PostgreSQL.

1. Create new database `inventory` using the script below:
```
CREATE DATABASE inventory
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;
```

Copy `application.properties` file from `resources` folder and change PostgreSQL settings as needed<br><br>
<b>To run the application:</b>
`java -jar release/inventory-api-1.0.0.jar -Dconfig.location=/path/to/application.properties`

After application running for the first time, it will automatically create the tables.

<h3>Using the API:</h3>

- API to retrieve products:<br>
GET http://localhost:8080/retrieveProducts <br>
```
{
  "id": "(string) unique id of the event",
  "timestamp": "(timestamp) utc timestamp of the event",
  "products": [
    {
      "id": "(long) id of the product"
    }
  ]
}
```
Example:
```
curl --request GET \
  --url http://localhost:8080/retrieveProducts \
  --header 'content-type: application/json' \
  --data '{
  "id": "TEST-1",
  "timestamp": "2018-11-06T22:16:39.238Z",
  "products": [
    {
      "id": 1
    },
    {
      "id": 2
    },		
    {
      "id": 3
    }		
  ]
}'
```

- API to store products:<br>
POST http://localhost:8080/storeProducts
```
{
  "id": "(string) unique id of the event",
  "timestamp": "(timestamp) utc timestamp of the event",
  "products": [
    {
      "id": "(long) id of the product",
      "name": "(string) name of the product",
      "quantity": "(integer) quantity of the product",
      "sale_amount": "(double) total sale amount"
    }
  ]
}
```
Example:
```
curl --request POST \
  --url http://localhost:8080/storeProducts \
  --header 'content-type: application/json' \
  --data '{
  "id": "TEST-1",
  "timestamp": "2018-11-06T22:16:39.238Z",
  "products": [
    {
      "id": 1,
      "name": "Test product 1",
      "quantity": 10,
      "sale_amount": 10000
    },
    {
      "id": 2,
      "name": "Test product 2",
      "quantity": 20,
      "sale_amount": 20000
    }
  ]
}'
```

- API to check status:<br>
GET http://localhost:8080/status


<h3>Database (PostgreSQL 9.6) tables:</h3>
- event<br>
  - id (bigint)<br>
  - event_id (character)<br>
  - timestamp (timestamp)<br>
  - type (integer)<br>
  - products (character)<br>
<br>
- product<br>
  - id (bigint)<br>
  - name (character)<br>
  - quantity (integer)<br>
  - sale_amount (double)<br>

<h3>TODO List: </h3>
- unit tests<br>
