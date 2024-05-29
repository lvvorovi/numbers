# NUMBERS

App to identify country using provided phone number.

### Configuration

Env vars for setup:

```
MYSQL_HOST - address of DB. default "localhost"
MYSQL_PORT - port on which DB is deployed. default 3306
MYSQL_SCHEMA_NAME - default name is "numbers"
MYSQL_USER - DB user name. default "root"
MYSQL_PASS - DB user password. default "root"
SERVER_PORT - port on which app to be deployed. default 8088.
```

## Running in Docker

Get a docker running.
Configure MySql DB to run on a standard port.
Prepare credentials in DB to use in application (below example uses root).

```shell
docker run --name numbers -d -p 8088:8088 --restart=always --net custom-net -e MYSQL_HOST=172.25.0.1 -e MYSQL_PORT=3306 -e MYSQL_SCHEMA_NAME=numbers -e MYSQL_USER=root -e MYSQL_PASS=root -e SERVER_PORT=8088 lvvorovi/numbers:latest
```
>adjust network variables as per your environment

Or...

## Try it out

use [this link](http://ec2-13-50-3-174.eu-north-1.compute.amazonaws.com:8088/) to test the app

## Features

* identify country by number
