pipeline {
	agent any 
    environment{
        VERSION = "${env.BUILD_ID}"
    }
    stages {
        stage('Construct Code') {
           steps {
               script {
                   // The below will clone your repo and will be checked out to master branch by default.
				   git credentialsId: 'GIT_CREDENTIAL', url: 'https://github.com/surjeetmohanty84/demo-app.git'
               }
           }
        }
         stage("docker build & docker push"){
            steps{
                script{
                   withCredentials([usernameColonPassword(credentialsId: 'Docer_Credential', variable: 'dockercredential')]) {
                             
			   sh '''
                                docker build -t dockerrock123/springdemo:${VERSION} .
                                docker login -u dockerrock123 -p dockerrock123 dockerrock123:8083 
                                docker push  dockerrock123/springdemo:${VERSION}
                                docker rmi dockerrock123/springdemo:${VERSION}
                            '''
                    }
                }
            }
        }
   }
    
}
