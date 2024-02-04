# to build in quarkus native mode if graalVM exists on your operating system
mvn package -Dqaurkus.package.type=native

# to build and run in quarkus native mode if graalVM not exists on your operating system 
mvn package -Dqaurkus.package.type=native -Dqaurkus.native.container-build=true
also you could add property Dqaurkus.container-image.build=true to build as docker image

./target/rest-number-runner (linux binary - on MacOs may error occur)
