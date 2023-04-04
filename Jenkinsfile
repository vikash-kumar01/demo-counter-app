pipeline{

    agent any
    tools {
        maven 'maven'
    }

    stages {

        stage('Git Checkout'){

            steps{

                   git branch: 'main', credentialsId: 'git_credentials', url: 'https://github.com/VTS-New/demo-counter-app1.git'
            }
        }
     stage('UNIT testing'){

            steps{

                sh 'mvn test'
	    } 
	}
     stage('Integration testing'){

            steps{

                sh 'mvn verify -DskipUnitTests'
            }
        }
     stage('Maven Build'){
      steps{
        sh 'mvn clean install'
       }
     }
   stage('Static code analysis'){
    stage{
         withSonarQubeEnv(credentialsId: 'sonar_api') {
         sh 'mvn clean package sonar:sonar'
      }
     }
    }  
   }
  }
}
