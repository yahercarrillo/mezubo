
# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp/
ADD storage.war app-storage.war
#RUN bash -c 'touch app.jar'
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=dev","app-storage.war"]

#docker build -t mezubo-bck-storage .

#docker run --name mezubo-bck-storage -d -p 8087:8087 mezubo-bck-storage



