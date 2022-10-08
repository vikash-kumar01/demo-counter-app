pipeline{
    
    agent any
    
    stages{
    
        stage('git checkout'){
         steps{
            git branch: 'main', url: 'https://github.com/vikash-kumar01/demo-counter-app.git'
         }
        }
        stage('UNIT TEST'){

            steps {
                sh 'mvn test'
            }
        }

        stage('INTEGRATION TEST'){

            steps {
                sh 'mvn verify -DskipUnitTests'
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
            script{
              def readPomVersion = readMavenPom file: 'pom.xml'
              def nexusRepoName = readPomVersion.version.endsWith("SNAPSHOT") ? "demoapp-snapshot" : "demoapp-release"
              nexusArtifactUploader artifacts: [
                [
                  artifactId: 'springboot',
                  classifier: '', 
                  file: 'target/Uber.jar',
                  type: 'jar'
                  ]
                ], 
                credentialsId: 'nexus3',
                groupId: 'com.example',
                nexusUrl: '3.226.244.16:8081', 
                nexusVersion: 'nexus3', 
                protocol: 'http', 
                repository: nexusRepoName, 
                version: "${readPomVersion.version}"
            }
          }
      }
        
    }
    
}