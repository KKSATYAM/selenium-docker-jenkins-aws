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

steps{

sh "docker push  keshrsa/selenium-docker-via-git"

}


}



}



}
