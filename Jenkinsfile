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
                def readPomVersion  = readMavenPom file: 'pom.xml'
                def nexusRepo  = readPomVersion.version.endsWith("SNAPSHOT") ? "demo-snapshot" : "demo-application"
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
            version: "${readPomVersion.version}"
            }
        }
      }    
      stage('Docker Image Build'){
        steps{
            script{
                sh 'docker image build -t $JOB_NAME:v1.$BUILD_ID .'
                sh 'docker image tag $JOB_NAME:v1$BUILD_ID ms21/$JOB_NAME:v1.$BUILD_ID'
                sh 'docker image tag $JOB_NAME:v1$BUILD_ID ms21/$JOB_NAME:latest'
            }
        }
      }

   }   
}  
