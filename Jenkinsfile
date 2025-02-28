pipeline {
    agent any
  
    tools {
        maven 'Maven'
    }

    environment {
        DOCKERHUB_CREDENTIALS = credentials('generalnitin-dockerhub')
        TAG = getCommitHash()
    }

//     triggers {
//         pollSCM('* * * * *')
//     }

    stages {
        stage("Compile and Build") {
            steps {
                echo 'Compile and Build the project...'
                sh "mvn clean install -DskipTests"
            }
        }
    
        stage("Test") {
            steps {
                echo 'Test the project...'
                sh "mvn test"
            }
        }

        stage("Code Analysis") {
            agent any
            steps {
                withSonarQubeEnv('SonarCloud') {
                    echo 'Static code analysis with SonarQube...'
                    sh 'mvn clean package -DskipTests sonar:sonar'
                }
            }
        }

//     stage("Quality Gate") {
//         steps {
//             timeout(time: 10, unit: 'MINUTES') {
//                 waitForQualityGate abortPipeline: true
//             }
//         }
//     }

//         stage('Deploy to Docker Hub') {
//             parallel {
                stage('Deploy Cloud-Gateway') {
                    steps {
                        sh 'docker build ./cloud-gateway -t generalnitin/devops-cloud-gateway:${TAG}'
                        withCredentials([string(credentialsId: 'generalnitin-dockerhub', variable: 'docker_pwd')]) {
                            sh "docker login -u generalnitin -p ${docker_pwd}"
                        }
                        sh "docker push generalnitin/devops-cloud-gateway:${TAG} "
                    }
                    post {
                        always {
                            sh 'docker logout'
                        }
                    }
                }

                stage('Deploy Order-Service') {
                    steps {
                        sh 'docker build ./order-service -t generalnitin/devops-order-service:${TAG}'
                        withCredentials([string(credentialsId: 'generalnitin-dockerhub', variable: 'docker_pwd')]) {
                            sh "docker login -u generalnitin -p ${docker_pwd}"
                        }
                        sh "docker push generalnitin/devops-order-service:${TAG} "
                    }
                    post {
                        always {
                            sh 'docker logout'
                        }
                    }
                }
                stage('Deploy Payment-Service') {
                    steps {
                        sh 'docker build ./payment-service -t generalnitin/devops-payment-service:${TAG}'
                        withCredentials([string(credentialsId: 'generalnitin-dockerhub', variable: 'docker_pwd')]) {
                            sh "docker login -u generalnitin -p ${docker_pwd}"
                        }
                        sh "docker push generalnitin/devops-payment-service:${TAG} "
                    }
                    post {
                        always {
                            sh 'docker logout'
                        }
                    }
                }
                stage('Deploy Product-Service') {
                    steps {
                        sh 'docker build ./product-service -t generalnitin/devops-product-service:${TAG}'
                        withCredentials([string(credentialsId: 'generalnitin-dockerhub', variable: 'docker_pwd')]) {
                            sh "docker login -u generalnitin -p ${docker_pwd}"
                        }
                        sh "docker push generalnitin/devops-product-service:${TAG} "
                    }
                    post {
                        always {
                            sh 'docker logout'
                        }
                    }
                }
                stage('Deploy Service-Registry') {
                    steps {
                        sh 'docker build ./service-registry -t generalnitin/devops-service-registry:${TAG}'
                        withCredentials([string(credentialsId: 'generalnitin-dockerhub', variable: 'docker_pwd')]) {
                            sh "docker login -u generalnitin -p ${docker_pwd}"
                        }
                        sh "docker push generalnitin/devops-service-registry:${TAG} "
                    }
                    post {
                        always {
                            sh 'docker logout'
                        }
                    }
                }
//             }
//         }
    }
}
def getCommitHash(){
    def commitHash = sh label: '', returnStdout: true, script: 'git rev-parse --short HEAD'
    return commitHash
}