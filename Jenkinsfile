pipeline{
    
    agent any
    
    stages{
    
        stage('git checkout'){
         steps{
            git branch: 'main', url: 'https://github.com/vikash-kumar01/demo-counter-app.git'
         }
        }
        stage('Mvn build'){
            
            steps{
            
               sh "mvn clean install"
                
            }
        }
        stage('SonarQube analysis'){
            steps{
                script{
                     withSonarQubeEnv(credentialsId: 'sonar-api-key') {
                      sh 'mvn clean package sonar:sonar'
                      cd 'target'
                      sh 'ls -lart'
                 }
                }
            }
        }
        stage("Quality Gate"){
          steps{
             script{
          timeout(time: 1, unit: 'HOURS') {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
              }
            }
       }
      }
      stage('upload war to nexus'){
          steps{
              nexusArtifactUploader artifacts: [
                [
                  artifactId: 'springboot',
                  classifier: '', 
                  file: 'target/Uber-1.0.0.war',
                  type: 'war'
                  ]
                ], 
                credentialsId: 'nexus3',
                groupId: 'com.example',
                nexusUrl: '3.226.244.16:8081', 
                nexusVersion: 'nexus3', 
                protocol: 'http', 
                repository: 'demoapp-release', 
                version: '1.0.0'
          }
      }
        
    }
    
}