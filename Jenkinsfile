pipeline {
    
    agent any
    
    stages{
        
        stage('Git CheckOut')
        {
            steps{
               echo "Deploying from source ${params.FROM_BUILD}"
               git branch: 'main', url: 'https://github.com/vikash-kumar01/SpringBoot_Counter_Application.git'
            }
        }
    }
}
