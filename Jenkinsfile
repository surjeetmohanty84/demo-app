pipeline {
    agent any
    environment {
        VERSION="${env.BUILD_ID}"
    }
    stages {
        stage('Example Build') {
           steps {
               script {
                   git credentialsId: 'GIT_CREDENTIAL', url: 'https://github.com/surjeetmohanty84/demo-app.git'          
               }
           }
        }
        stage('Maven Build') {
           steps {
               script {
                   	    def mavenHome= tool name: "maven", type: "maven"
	    				def cmd= "${mavenHome}/bin/mvn"
	    				bat "${cmd} clean install"
               }
           }
        }
        stage('Sonar Qube Test') {
           steps {
               script {
                   withSonarQubeEnv(credentialsId: 'sonar-credential') {
		 	   			def mavenHome= tool name: "maven", type: "maven"
	    				def cmd= "${mavenHome}/bin/mvn"
	    				bat "${cmd} sonar:sonar"
		}
               }
           }
        }
    }
}