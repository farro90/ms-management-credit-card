FROM openjdk:11
VOLUME /tmp
EXPOSE 8109
ADD ./target/ms-management-credit-card-0.0.1-SNAPSHOT.jar ms-management-credit-card.jar
ENTRYPOINT ["java","-jar","ms-management-credit-card.jar"]