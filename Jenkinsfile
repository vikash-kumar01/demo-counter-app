pipeline{
    agent any
    tools {
        maven 'maven'
    }
stages{
    stage('checkout from SCM'){
        steps{
            git branch: 'main', credentialsId: 'git_credentials', url: 'https://github.com/VTS-New/demo-counter-app1.git'
        }
    }
    stage('Unit Test'){
        steps{
            sh 'mvn test'
                }
            }
    stage('Intergration Testing'){
        steps{
            sh 'mvn verify -DskipunitTests'
                }
            }
    stage('Maven Build'){
        steps{
            sh 'mvn clean install'
        }
    }
    stage('static code analysis'){
        steps {
            script{
                 withSonarQubeEnv(credentialsId: 'Sonar_Credential') {
                    sh 'mvn clean package sonar:sonar'
                   }
                 }
            }
        }
     stage('Quality gate'){
        steps{
            script{
                waitForQualityGate abortPipeline: false, credentialsId: 'Sonar_Credential'
            }
        }
     } 
     stage('Upload WAR file to nexus'){
        steps{
            script{

            
               nexusArtifactUploader artifacts: 
               [
                  [
                    artifactId: 'springboot',
                    classifier: '', file: 'target/Uber.jar',
                    type: 'jar'

                ]
            ], 
            credentialsId: 'nexus_access',  
            groupId: 'com.example', 
            nexusUrl: '18.61.34.157:8081', 
            nexusVersion: 'nexus3', 
            protocol: 'http', 
            repository: nexusRepo, 
            version: '2.0.0'
            }
        }
      }     
   }   
}  
