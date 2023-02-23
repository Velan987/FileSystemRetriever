# Introduction
Jenkins "pipeline shared libraries" are configured at folder level. Jenkins is also providing a functionality to load
the library dynamically using "library identifier", but it has the limitation that it can only load the library from SCM.
FileSystemRetriever is a java library developed to overcome this limitation.


# Development

## To build$ ./gradlew clean jpi

## Create Jenkins job for testing
To test FileSystemRetriever need library files in jenkins agent and jenkins job to load the library.
	Create logger.groovy under test-library/var/ directory with below content
	
	#!/groovy
	def info(message) {
		echo message
	}
	

# Configure FileSystemRetriever plugin with Pipeline Jenkins Job
	
	pipeline {
		agent any
		stages {
			stage("Test FileSystemRetriever"){
				steps{
					script{
						library identifier: 'test-library@0.0.1', retriever: fileSystemRetriever(
						libraryPath: 'C:\\test-library')
						log.info("info message")
					}
				}
			}
		}
	}
