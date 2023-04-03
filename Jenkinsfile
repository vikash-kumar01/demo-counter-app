pipeline{

    agent any

    stages {

        stage('Git Checkout'){

            steps{

                   git branch: 'main', credentialsId: 'git_credentials', url: 'https://github.com/VTS-New/demo-counter-app1.git'
            }
        }
     stage(''UNIT testing'){

            steps{

                sh 'mvn test'
	    }  
         
	  }

	}
   
    }
}
