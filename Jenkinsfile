pipeline {
    agent any

    tools {
        jdk 'JDK-17'
        maven 'Maven-3.9'
    }

    environment {
        // 🔑 Critical: Export JAVA_HOME so Maven finds Java
        JAVA_HOME = "${tool 'JDK-17'}"
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }

    stages {
        // ❌ REMOVE this entire stage - Jenkins already checked out code!
        // stage('Checkout') { ... } ← DELETE THIS BLOCK

        stage('Build & Test') {
            steps {
                // Optional: Debug output to verify Java is found
                sh 'echo "JAVA_HOME: $JAVA_HOME"'
                sh 'java -version'
                
                // Run Maven tests
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
}s