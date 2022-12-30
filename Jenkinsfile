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
                script{
                   def mvnHome = tool name: 'maven-3.8.6', type: 'maven'
                   withSonarQubeEnv(credentialsId: 'jenkins-sonar') {
                   sh "${mvnHome}/bin/mvn clean package sonar:sonar"
                  }
                } 
            }
        }
        stage('Quality Gate status'){
            steps{
                script{
                    waitForQualityGate abortPipeline: false, credentialsId: 'jenkins-sonar'
                }
            }
        } 
        stage('nexus-auth'){
            steps{
                script{
                     def readPomVersion = readMavenPom file: 'pom.xml'

                     def nexusRepo =  readPomVersion.version.endsWith("SNAPSHOT") ? "megaproject-snapshot" : "megaproject-release"

                    nexusArtifactUploader artifacts:
                    [
                        [
                            artifactId: 'springboot', 
                            classifier: '', 
                            file: 'target/Uber.jar', 
                            type: 'jar'

                        ]
                    ],
                             credentialsId: 'nexus-auth', 
                             groupId: 'com.example', 
                             nexusUrl: '54.160.38.178:8081', 
                             nexusVersion: 'nexus3', 
                             protocol: 'http', 
                             repository: nexusRepo, 
                             version: "${readPomVersion.version}"
                }
            }
        }
        stage('Docker Image build'){
            steps{
                script{
                    sh 'docker image build -t $JOB_NAME:v1.$BUILD_ID .'
                    sh 'docker image tag $JOB_NAME:v1.$BUILD_ID sreekanth99490/$JOB_NAME:v1$BUILD_ID'
                    sh 'docker image tag $JOB_NAME:v1.$BUILD_ID sreekanth99490/$JOB_NAME:latest'
                }
            }
      
        }
        stage('Push image to Dockerhub'){
            steps{
                script{
                  withCredentials([string(credentialsId: 'docker_pass', variable: 'docker_pass')]) {
                    sh 'docker login -u sreekanth99490 -p ${docker_pass}'
                    sh 'docker image push sreekanth99490/$JOB_NAME:v1$BUILD_ID'
                    sh 'docker image push sreekanth99490/$JOB_NAME:latest'
                    }        
                }
            }
        }
    
    }
}
