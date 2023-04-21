pipeline {
    agent any
    tools {
        maven 'maven'
        dockerTool 'docker'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/kermakovich/hospital-users.git'
            }
        }
        stage('Maven build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Docker build') {
            steps {
                sh 'docker build -t kermakovich/hospital-users .'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'DOCKERHUB_KERMAKOVICH',
                usernameVariable: 'DOCKER_USERNAME',
                passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                    sh "docker push kermakovich/hospital-users"
                }
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                withCredentials([file(credentialsId: 'KUBECONFIG', variable: 'KUBECONFIG')]) {
                    sh 'cd ../infra'
                    sh 'kubectl apply -f configmap.yml'
                    sh 'kubectl apply -f deployment.yml'
                    sh 'kubectl apply -f service.yml'
                    sh 'kubectl apply -f service-account.yml'
                }
            }
        }
    }
}