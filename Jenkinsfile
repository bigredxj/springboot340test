pipeline {
    agent {
        docker {
            image 'centos7-dev:2.0'
            args '-v /mnt/disk2/java-repository:/java-repository'
        }
    }
    environment {
           GRADLE_USER_HOME=/java-repository
        }
    stages {
        stage('Build') {
            steps {
                sh 'gradle build'
            }
        }
    }
}