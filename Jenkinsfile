pipeline{
    agent any
    tools {
        maven 'Maven'
    }
stages{
    stage('checkout from SCM'){
        steps{
            git branch: 'main', url: 'https://github.com/VTS-New/demo-counter-app1.git'
        }
    }
    stage('UNIT Test'){
        steps{
            sh 'mvn test'
        }
    }
  }
}
