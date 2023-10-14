FROM ghcr.io/ntgelite/based-java-image:latest
ADD target/product-0.0.1-SNAPSHOT.jar product-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/product-0.0.1-SNAPSHOT.jar"]
