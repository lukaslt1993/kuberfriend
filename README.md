## About
A playground to explore kubernetes. Project consists of:

- base application made with Vaadin and Spring Boot. It's called kuberfriend in pom.xml. Runs on 8081 port.
- kuberfriend-service - the "brains" of kuberfriend. Made with Spring Boot, runs on 8082 port.
- MongoDB - used by both applications, runs on 27017 port.

## How does it work
1. on initialisation, kuberfriend loads the DB with .txt files from __src/main/resources__ where I added some
books as an initial data. The books were obtained from [project gutenberg](https://www.gutenberg.org/) which
legally stores free books.
2. kuberfriend accepts text as an input and sends it to kuberfriend-service via RestTemplate.
3. the service gets books from the DB, and returns sentences matching the user input. One sentence for each
book.
4. these sentences are then displayed to the user.
## Usage
### Local
First start MongoDB by executing __docker-compose up__ command from __docker/mongo__ directory. Then run
kuberfriend and kuberfriend-service from IDE/Maven/etc.
### Note for Docker/Kubernetes
Kuberfriend's and kuberfriend-service's JARs are copied from the corresponding __target__ folder to docker
container. This means, you have to first do __mvn clean package -P production__ for the both apps.
### Docker
Execute __docker-compose up__ command from __docker__ directory.
### Kubernetes
Execute __docker-compose build__ command from __docker__ directory. Then execute __kubectl apply -f .__
command from __kubernetes__ directory.
### Opening kuberfriend's UI
Go to localhost:8081


