pipeline {
 agent any
  stages {
    stage('git checkout') {
     steps {
       git branch: 'main', url: 'https://github.com/akhilprabhu777/demo-counter-app.git'
}
}
   stage('unit testing') {
    steps {
      sh 'mvn clean'
}
}
   stage('Integration testing') {
    steps {
      sh 'mvn verify -DskipUnitTests'
}
}
   stage('maven build') {
    steps {
      sh 'mvn clean install'
}
}
   stage('Static code analysis'){
      steps{   
        script{
            withSonarQubeEnv(credentialsId: 'sonar-apikey') {
                 sh 'mvn clean package sonar:sonar'
                    }
                   }
                }
            }
}
}
