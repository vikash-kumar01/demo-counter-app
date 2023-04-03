pipeline{

    agent any
       stages {

        stage('Git Checkout'){

            steps{
                script {

                    git branch: 'main', url: 'https://github.com/VTS-New/demo-counter-app1.git'
                }
            }
        }
        stage('UNIT testing'){

            steps{
               script {
                    sh 'mvn test'
              }
            }
        }
    }
}
