FROM openjdk:8

ADD . /project

VOLUME /project

WORKDIR /project

ENTRYPOINT ["java", "-jar", "target/twitter-extractor-jar-with-dependencies.jar"]