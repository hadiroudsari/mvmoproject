# mvmoproject
(google oauth2 authorization using java)

This project used docker for Wildfly application server.
for running the application you need go to docker folder run the docker and a
```bash

docker container run -d --name=pg -p 5432:5432 -e POSTGRES_PASSWORD=1234 -e PGDATA=/pgdata -v /pgdata:/pgdata postgres:12


```

mvmo use  google oauth 2 API and creat new user into database if the user sighn in with gmail for the first time. This project has a lot more cool thing to do but it is more for testing different technologies.
