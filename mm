stage('Static code analysis'){
            
            steps{
                
                script{
                    
                   withSonarQubeEnv(credentialsId: 'sonar_api') {
                        
                        sh 'mvn clean package sonar:sonar'
                    }
                   }   
                }
            }