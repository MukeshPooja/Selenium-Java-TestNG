pipeline {
    agent any

    tools {
        jdk 'JDK-17'
        maven 'Maven-3.9'
    }

    environment {
        JAVA_HOME = "${tool 'JDK-17'}"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        stage('Build & Test') {
            steps {
                sh 'echo "JAVA_HOME: $JAVA_HOME"'
                sh 'java -version'
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