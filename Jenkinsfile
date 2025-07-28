pipeline {
    agent any

    environment {
        DOCKER_IMAGE   = "harpia-ms-telemetria:latest"
        DEPLOY_USER    = "tivic"
        DEPLOY_SERVER  = "192.168.1.161"
        DEPLOY_PATH    = "/tivic/harpia-ms-telemetria"
        SSH_CRED_ID    = "ssh-cred-id"
        ENV_CRED_ID    = "harpia-ms-telemetria-env"
        CERT_CLIENT_ID = "cert-client-p12"
        CERT_JKS_ID    = "cert-rabbit-jks"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Preparar Certificados ZIP') {
            steps {
                withCredentials([
                    file(credentialsId: env.CERT_CLIENT_ID, variable: 'CLIENT_CERT'),
                    file(credentialsId: env.CERT_JKS_ID, variable: 'JKS_CERT')
                ]) {
                    sh '''
                        echo ">> Gerando ZIP dos certificados..."
                        mkdir -p build_certs
                        cp "$CLIENT_CERT" build_certs/client.p12
                        cp "$JKS_CERT" build_certs/rabbit_truststore.jks
                        chmod 600 build_certs/*
                        cd build_certs && zip ../certs.zip * && cd ..
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                    echo ">> Buildando imagem Docker do harpia-ms-telemetria..."
                    docker build -t $DOCKER_IMAGE .
                '''
            }
        }

        stage('Enviar Imagem para Servidor') {
            steps {
                sshagent([env.SSH_CRED_ID]) {
                    sh '''
                        echo ">> Enviando imagem Docker para o servidor remoto..."
                        docker save $DOCKER_IMAGE | gzip | ssh -o StrictHostKeyChecking=no $DEPLOY_USER@$DEPLOY_SERVER "gunzip | docker load"
                    '''
                }
            }
        }

        stage('Enviar .env para Servidor') {
            steps {
                withCredentials([file(credentialsId: env.ENV_CRED_ID, variable: 'ENV_FILE')]) {
                    sshagent([env.SSH_CRED_ID]) {
                        sh '''
                            echo ">> Enviando .env para o servidor remoto..."
                            ssh -o StrictHostKeyChecking=no $DEPLOY_USER@$DEPLOY_SERVER "mkdir -p $DEPLOY_PATH"
                            scp -o StrictHostKeyChecking=no $ENV_FILE $DEPLOY_USER@$DEPLOY_SERVER:$DEPLOY_PATH/.env
                        '''
                    }
                }
            }
        }

        stage('Enviar Certificados ZIP') {
            steps {
                sshagent([env.SSH_CRED_ID]) {
                    sh '''
                        echo ">> Enviando certificados ZIP para o servidor remoto..."
                        ssh -o StrictHostKeyChecking=no $DEPLOY_USER@$DEPLOY_SERVER "mkdir -p $DEPLOY_PATH"
                        scp -o StrictHostKeyChecking=no certs.zip $DEPLOY_USER@$DEPLOY_SERVER:$DEPLOY_PATH/certs.zip
                        ssh -o StrictHostKeyChecking=no $DEPLOY_USER@$DEPLOY_SERVER "cd $DEPLOY_PATH && unzip -o certs.zip && rm certs.zip"
                    '''
                }
            }
        }

        stage('Deploy Remoto via docker-compose') {
            steps {
                sshagent([env.SSH_CRED_ID]) {
                    sh '''
                        echo ">> Subindo aplicação com docker-compose..."
                        ssh -o StrictHostKeyChecking=no $DEPLOY_USER@$DEPLOY_SERVER "
                            cd $DEPLOY_PATH &&
                            docker-compose up -d
                        "
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline do microserviço harpia-ms-telemetria concluído com sucesso!"
        }
        failure {
            echo "❌ Falha no pipeline do microserviço harpia-ms-telemetria!"
        }
    }
}
