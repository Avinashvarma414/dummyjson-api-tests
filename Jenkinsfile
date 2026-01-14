pipeline {
    agent any

    tools {
        jdk 'jdk17'
        maven 'maven'
    }

    environment {
        ENV = 'qa'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Run Smoke Tests') {
            steps {
                sh 'mvn test -Denv=qa -Dsurefire.suiteXmlFiles=testng-smoke.xml'
            }
        }
    }
    post {
        always {
            echo 'Pipeline finished'
        }
    }
}
