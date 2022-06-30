node{
    environment{
        VERSION="${env.BUILD_ID}"
    }

    stage('Git Clone'){
           git credentialsId: 'GIT_CREDENTIAL', url: 'https://github.com/surjeetmohanty84/demo-app.git'
    }
	stage('Maven Build'){
	    
	    def mavenHome= tool name: "maven", type: "maven"
	    def cmd= "${mavenHome}/bin/mvn"
	    bat "${cmd} clean install"
	}
	stage('Sonar Qube Test'){
		withSonarQubeEnv(credentialsId: 'sonar-credential') {
		    def mavenHome= tool name: "maven", type: "maven"
	    	def cmd= "${mavenHome}/bin/mvn"
	    	bat "${cmd} sonar:sonar"
	
		}
		stage('Build Docker Image'){
		             bat "docker build -t springapp ."
		             bat "docker tag springapp dockerrock123/springapp:${VERSION}"    
		             }
		stage('Docker Push'){
		    echo ("Docker push")
		    withCredentials([string(credentialsId: 'DockerHub_Credential', variable: 'DockerHub_Credential')]) {
 				bat "docker login -u dockerrock123 -p ${DockerHub_Credential}"
			}
			bat "docker push dockerrock123/springapp:v12"
		}
		
		stage('Kubernetes Deployment'){
		    kubernetesDeploy(
		    	configs: 'deployment.yml',
		    	kubeconfigId: 'KUBERNETES_CLUSTER',
		    	enableConfigSubstitution: true
		    )
		}

	                 
	             }


}
