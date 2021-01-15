FROM openjdk:15
COPY ./target/boulot-jobfinder-0.0.1-SNAPSHOT.jar boulot-jobfinder-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","boulot-jobfinder-0.0.1-SNAPSHOT.jar"]