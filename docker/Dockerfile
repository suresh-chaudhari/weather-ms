FROM openjdk:8u181-jdk

MAINTAINER suresh26272@gmail.com

# setup group and user, initialize home
RUN addgroup -gid 30002 loyaltyone-ms && \
	adduser --gid 30002  --system --disabled-login --home /app --shell /sbin/nologin -u 30002 loyaltyone-ms

COPY loyaltyone-ms.jar /app

RUN chown -R loyaltyone-ms /app

EXPOSE 8080 8443

USER loyaltyone-ms

WORKDIR app

ENTRYPOINT exec java $JAVA_OPTS -jar ./loyaltyone-ms.jar