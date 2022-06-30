node{
    
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
		             bat "docker tag springapp dockerrock123/springapp:v12"    
		             }
		stage('Docker Push'){
		    echo ("Docker push")
		}

	                 
	             }


}
