pipeline {
    agent any
    stages {
        stage('Run tests') {
            steps {
                sh "./mvnw -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    sh "./gradlew sonarqube"
                }
            }
        }
        stage("Quality gate") {
            steps {
                waitForQualityGate abortPipeline: true
            }
        }
    }
}