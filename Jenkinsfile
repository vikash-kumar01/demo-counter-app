pipeline{
    agent any
    stages{
        stage('Git Checkout'){
            steps{
                git branch: 'main', url: 'https://github.com/99North/Demo-Counter-App.git'
            }
        }
        
        stage('Unit Testing'){
            steps{
                sh 'mvn test'
            }
        }

        stage('Intigration Testing'){
            steps{
                sh 'mvn verify -DskipUnitTests'
            }
        }

        stage('Maven Build'){
            steps{
                sh 'mvn clean install'
            }
        }
        
        
        stage('Static Code Analysis'){
            steps{
                script{
                    withSonarQubeEnv(credentialsId: 'sonar-api-key') {
                    sh "mvn clean package sonar:sonar"
                        }
                }
            }
        }
        
        stage('Quality Gate Status'){
            steps{
                script{
                    waitForQualityGate abortPipeline: false, credentialsId: 'sonar-api-key'
                }
            }
        }
        
        stage('Upload War file to Nexus'){
            steps{
                script{
                    
                    
                    nexusArtifactUploader artifacts: 
                    [
                        [
                            artifactId: 'springboot', classifier: '', file: 'target/Uber.jar', type: 'jar'
                            ]
                            ], 
                            credentialsId: 'nexus-auth-jen', 
                            groupId: 'com.example', 
                            nexusUrl: '44.199.225.221:8081', 
                            nexusVersion: 'nexus3', 
                            protocol: 'http', 
                            repository: 'demoapp-release', 
                            version: '1.0.2'
                }
            }
        }
       
       
       
       
    }
}
