pipeline{
    
    agent any 
    
    stages {
        
        stage('Git Checkout'){
            
            steps{
                
                script{
                    
                    git branch: 'main', url: 'https://github.com/nivellef/demo-counter-app.git'
                    
                }
            }
        }
        stage('UNIT testing'){
            
            steps{
                
                script{
                    
                    sh 'mvn test'
                }
            }
        }
        stage('Integration testing'){
            
            steps{
                
                script{
                    
                    sh 'mvn verify -DskipUnitTests'
                }
            }
        }
        stage('Maven build'){
            
            steps{
                
                script{
                    
                    sh 'mvn clean install'
                }
            }
        }
       stage('Static code analysis'){
            
            steps{
                
                script{
                    
                  sh 'mvn sonar:sonar \
  -Dsonar.projectKey=Demo-Counter \
  -Dsonar.host.url=http://54.83.233.123:9000 \
  -Dsonar.login=dbf1a2282c677f0a3908c4194708f4c0dc65d31b'
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
        }
        
}
