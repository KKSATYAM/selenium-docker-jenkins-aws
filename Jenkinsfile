pipeline{

agent any

stages{

stage('Build Jar'){

steps{

sh "mvn clean package -DskipTests"


}
}
stage('Build Image'){

steps{

sh "docker build -t keshrsa/selenium-docker-via-git ."

}

}

stage('Push Image'){

environment{

DOCKER_HUB=credentials('dockerhub-creds')

}

steps{

sh 'docker login -u ${DOCKER_HUB_USR} -p ${DOCKER_HUB_PSW}'
sh 'docker push  keshrsa/selenium-docker-via-git:latest'
sh "docker tag keshrsa/selenium-docker-via-git:latest keshrsa/selenium-docker-via-git:${env.BUILD_NUMBER}"
sh "docker push keshrsa/selenium-docker-via-git:${env.BUILD_NUMBER}"

}


}



}

post{

always{

sh "docker logout"

}
}
}
