pipeline {
    agent any

    tools {
        jdk 'JDK-17'
        maven 'Maven-3.9'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    url: 'https://github.com/MukeshPooja/Selenium-Java-TestNG.git',
                    credentialsId: 'github-credentials'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Archive Results') {
            steps {
                archiveArtifacts artifacts: 'target/surefire-reports/*.xml', allowEmptyArchive: true
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo '✅ Build successful!'
        }
        failure {
            echo '❌ Build failed - check logs'
        }
    }
}