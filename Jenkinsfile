pipeline {

   agent any 

      stages {
         
         stage('git checkout'){
            
            steps {
                
               git branch: 'main', url: 'https://github.com/vikash-kumar01/demo-counter-app.git'
                }

                }
           stage('Unit Testing'){

            steps {

               sh 'mvn test'
                }
                }

           stage('Maven Build'){

            steps {

               sh 'mvn clean install'
                }
              } 
           stage('Sonar Code Analysis'){

              steps {
                 scripts {
                     withSonarQubeEnv(credentials: 'sonarqubetoken') {
                       sh 'mvn clean package sonar:sonar'
                }
              }
              }
  }


}
