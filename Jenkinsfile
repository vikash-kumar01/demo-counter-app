pipeline {

    agent any 

    stages{

        stage('Git Checkout'){

            steps{
                git branch: 'main', url: 'https://github.com/vikash-kumar01/demo-counter-app.git'
            }
        }
        stage('UNIT Testing'){

            steps{
                sh 'mvn test'
            }
        }
        stage('Integration testing'){

            steps{
                sh 'mvn verify -DskipUnitTests'
            }
        }
    }
}