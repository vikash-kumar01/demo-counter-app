pipeline {
 agent any
  stages{
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
   stage('unit testing') {
    steps {
      sh 'mvn verify -DskipUnitTests'
}
}
}
}
