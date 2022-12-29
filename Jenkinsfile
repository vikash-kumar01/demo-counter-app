pipeline{
    agent any
    tools{
        tool name: 'maven-3.8.6', type: 'maven'
        tool name: 'java-11', type: 'jdk'
    }
    stages{
        stage('Git Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/gollasreekanth/demo-counter-app.git'    
            }
        }

        stage('UNIT testing'){
            
            steps{
                
                script{
                    
                    sh 'mvn test'
                }
            }
        }

    }
}