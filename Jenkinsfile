pipeline {
    agent any
    stages {
        stage('clean') {
            steps{
                sh "chmod +x mvnw"
                sh "./mvnw clean"
            }
                }
        stage('Run tests') {
            steps {
                sh "./mvnw -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
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