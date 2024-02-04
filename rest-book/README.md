# to build in quarkus native mode if graalVM exists on your operating system
mvn package -Dqaurkus.package.type=native

# to build and run in quarkus native mode if graalVM not exists on your operating system
mvn package -Dqaurkus.package.type=native -Dqaurkus.native.container-build=true
also you could add property Dqaurkus.container-image.build=true to build as docker image

./target/rest-book-runner  (linux binary - on MacOs may error occur)

# documentation
curl http:localhost:8702/q/openapi

# documentation in JSON format
curl http:localhost:8702/q/openapi -H "Accept: application/json"

# documentation (swagger)
open http:localhost:8702/q/swagger-ui

# dev console with used properties
http:localhost:8702/q/dev