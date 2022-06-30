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
	    def mavenHome= tool name: "maven", type: "maven"
	    def cmd= "${mavenHome}/bin/mvn"
	    bat "${cmd} sonar:sonar"
	                 
	             }


}
