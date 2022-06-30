pipeline{
    agent any 
    environment{
        VERSION = "${env.BUILD_ID}"
    }
    stages{
    	stage('Construct Code') {
           steps {
               script {
                   // The below will clone your repo and will be checked out to master branch by default.
				   git credentialsId: 'GIT_CREDENTIAL', url: 'https://github.com/surjeetmohanty84/demo-app.git'
               }
           }
        }
}    
}
