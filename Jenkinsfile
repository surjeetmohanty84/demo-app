pipeline {
    agent any
    environment {
        VERSION="${env.BUILD_ID}"
    }
    

    stages {
        stage('Git Clone') {
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
        stage('Docker Build') {
           steps {
               script {
                     bat "docker build -t springapp ."
		             bat "docker tag springapp dockerrock123/springapp:${VERSION}"
               }
           }
        }
        stage('Docker Push') {
           steps {
               script {
                   echo ("Docker push")
		    		withCredentials([string(credentialsId: 'DockerHub_Credential', variable: 'DockerHub_Credential')]) {
 						bat "docker login -u dockerrock123 -p ${DockerHub_Credential}"
					}
					bat "docker push dockerrock123/springapp:${VERSION}"
               }
           }
        }
        stage('Deploying ') {
           steps {
               script {
                   withCredentials([kubeconfigFile(credentialsId: 'KUBERNETES_CLUSTER', variable: 'KUBECONFIG')]) {
						bat "kubectl get nodes"
					}
               }
           }
        }
    }
}