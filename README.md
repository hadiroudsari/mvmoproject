# mvmoproject
(google oauth2 authorization using java)

This project used docker for contanerising Wildfly application server and postgreSQL database.
I use  google oauth 2 API and create new user into database if the user sign in with gmail for the first time. In addition,I configure dockerised wildfly with ssl certificate. This project has a lot more cool thing to do but it is more for testing different technologies.
for running the application you need go to docker folder run the docker and a
```bash

docker container run -d --name=pg -p 5432:5432 -e POSTGRES_PASSWORD=1234 -e PGDATA=/pgdata -v /pgdata:/pgdata postgres:12


```
Alternatively you can run both database and application from my dockerhub account use these command seperatly.
```
docker run -p 8080:8080 -p 8443:8443 hadiroudsari/maro:2

```
and
```
docker container run -d --name=pg -p 5432:5432 -e POSTGRES_PASSWORD=1234 -e PGDATA=/pgdata -v $(pwd)/pgdata:/pgdata hadiroudsari/pg:1

```


