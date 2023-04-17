pipeline{
    agent any
    tools {
        maven 'Maven'
    }
stages{
    stage('checkout from SCM'){
        steps{
            git branch: 'main', credentialsId: 'git_user', url: 'https://github.com/VTS-New/demo-counter-app1.git'
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
                 withSonarQubeEnv(credentialsId: 'sonar-api') {
                    sh 'mvn clean package sonar:sonar'
                   }
                 }
            }
        }
     stage('Quality gate'){
        steps{
            script{
                waitForQualityGate abortPipeline: false, credentialsId: 'sonar-api'
            }
        }
     } 
     stage('Upload WAR file to nexus'){
        steps{
            script{

                def readPomVersion = readMavenPom file: 'pom.xml'
                def  nexusRepo = readPomVersion.version.endswith("SNAPSHOT") ? "demo-snapshot" : "demo-application"
               nexusArtifactUploader artifacts: 
               [
                [
                    artifactId: 'springboot',
                    classifier: '', file: 'target/Uber.jar', 
                    type: 'jar'
                ]
            ], 
            credentialsId: 'nexu_auth', 
            groupId: 'com.example', 
            nexusUrl: '18.60.56.112:8081', 
            nexusVersion: 'nexus3', 
            protocol: 'http', 
            repository: 'nexusRepo', 
            version: "${readPomVersion.version}"
            }
        }
      }     
   }   
}  
