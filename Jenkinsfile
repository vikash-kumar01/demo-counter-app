pipeline{
    agent any
    stages{
        stage('Git Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/gollasreekanth/demo-counter-app.git'    
            }
        }

        stage('UNIT testing'){
            
            steps{
                
                script{
                    def mvnHome = tool name: 'maven-3.8.6', type: 'maven'
                    sh "${mvnHome}/bin/mvn package"
                }
            }
        }
        stage('Inegration Testing'){
            steps{
                script{
                def mvnHome = tool name: 'maven-3.8.6', type: 'maven'
                sh"${mvnHome}/bin/mvn verify -DskipUnitTests"
                }
            }
        }
        stage('Maven Build'){
            steps{
                script{
                    def mvnHome = tool name: 'maven-3.8.6', type: 'maven'
                    sh"${mvnHome}/bin/mvn clean install"
                }
            }
        }
        stage('Static Code Analysis'){
            steps{
                def mvnHome = tool name: 'maven-3.8.6', type: 'maven'
                withSonarQubeEnv(credentialsId: 'jenkins-sonar') {
                  sh "${mvnHome}/bin/mvn clean package sonar:sonar"
                } 
            }
        }

    }
}
