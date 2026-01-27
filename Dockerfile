FROM bellsoft/liberica-openjdk-alpine:latest

RUN apk add curl jq

WORKDIR /home/selenium-docker

ADD project/ .

ADD ./runner.sh .

#ENTRYPOINT java -cp 'libs/*' -Dselenium.grid.enabled=true -Dbrowser=${BROWSER} -Dselenium.grid.hubHost=${HUB_HOST} \
#	   org.testng.TestNG -threadcount ${THREAD_COUNT} test-suites/${TEST_SUITE}	

ENTRYPOINT sh ./runner.sh
