pipeline {	
	agent any	
	tools {	    
		maven 'my-maven'		
		jdk 'jdk-17'	
	}	
	stages {        
		stage('Clone'){			
			steps {git url:'https://github.com/AbhinavSdr/employee-service.git', branch:'master'}
		}
		stage('Build'){			
			steps {bat "mvn clean install -DskipTests"}		
		}		
		stage('Pre-Deploy'){
			steps{bat "docker rm -f employee-cntr"
						"docker rmi -f employee-img"}
		}
		stage('Deploy') {			
			steps { bat "docker build -t employee-img ."			    
			            bat "docker run -p 9082:8082 -d --name employee-cntr --network my-net employee-img"}		
		}		
	}
}