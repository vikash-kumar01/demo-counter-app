pipeline{
    agent any
stages{
    stage('checkout from SCM'){
        steps{
            git branch: 'main', credentialsId: 'gitpasswd', url: 'https://github.com/VTS-New/demo-counter-app1.git'
        }
    }
  }
}      
