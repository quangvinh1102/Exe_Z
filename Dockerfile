FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /build
COPY pom.xml .
COPY src ./src
RUN mvn -q package -DskipTests

FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /build/target/Nso-jar-with-dependencies.jar app.jar
COPY Data/ Data/
COPY item_roi/ item_roi/

ENV SERVER_HEADLESS=true
ENV JAVA_OPTS="-Xms512m -Xmx1536m -Dfile.encoding=UTF-8 -Djava.awt.headless=true"

EXPOSE 14444

CMD ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
