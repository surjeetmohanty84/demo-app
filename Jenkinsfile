pipeline{
    agent any 
    environment{
        VERSION = "${env.BUILD_ID}"
    }
    stages{
    	
        stage('Sonar Code Quality Test'){
                              
          	agent {
                  
          	    docker {
    	              image 'openjdk:11'
    	          }
                  
          	}
                    
          }

	}    
}
