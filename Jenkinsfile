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
                    sh "$(mvnHome)/bin/mvn package"
                }
            }
        }

    }
}