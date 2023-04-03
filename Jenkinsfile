pipeline{

    agent any
     tools {
         maven 'mvn'
     }

    stages {

        stage('Git Checkout'){

            steps{

                    git branch: 'main', url: 'https://github.com/VTS-New/demo-counter-app1.git'
            }
        }
        stage('UNIT testing'){

            steps{

                    sh 'mvn test'
                }
        }
    }
}
