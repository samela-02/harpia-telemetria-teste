pipeline {
    agent any

    environment {
        DOCKER_IMAGE   = "harpia-ms-telemetria:latest"
        DEPLOY_USER    = "tivic"
        DEPLOY_SERVER  = "192.168.1.161"
        DEPLOY_PATH    = "/tivic/harpia-ms-telemetria"
        SSH_CRED_ID    = "ssh-cred-id"             // Credencial SSH configurada no Jenkins
        ENV_CRED_ID    = "harpia-ms-telemetria-env"       // Secret file com o .env
        CERT_CLIENT_ID = "cert-client-p12"         // Secret file do client.p12
        CERT_JKS_ID    = "cert-rabbit-jks"         // Secret file do rabbit_truststore.jks
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Preparar Certificados') {
            steps {
                withCredentials([
                    file(credentialsId: env.CERT_CLIENT_ID, variable: 'CLIENT_CERT'),
                    file(credentialsId: env.CERT_JKS_ID, variable: 'JKS_CERT')
                ]) {
                    sh '''
                      echo ">> Copiando certificados para pasta local ./certs"
                      mkdir -p certs
                      cp "$CLIENT_CERT" certs/client.p12
                      cp "$JKS_CERT" certs/rabbit_truststore.jks
                      chmod 600 certs/client.p12 certs/rabbit_truststore.jks
                    '''
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                sh '''
                  echo ">> Iniciando build da imagem do microserviço..."
                  docker build -t $DOCKER_IMAGE .
                '''
            }
        }

        stage('Enviar Imagem para Servidor') {
            steps {
                sshagent([env.SSH_CRED_ID]) {
                    sh '''
                      echo ">> Exportando imagem para servidor remoto..."
                      docker save $DOCKER_IMAGE | gzip | ssh -o StrictHostKeyChecking=no $DEPLOY_USER@$DEPLOY_SERVER "gunzip | docker load"
                    '''
                }
            }
        }

        stage('Copiar .env para Servidor') {
            steps {
                withCredentials([file(credentialsId: env.ENV_CRED_ID, variable: 'ENV_FILE')]) {
                    sshagent([env.SSH_CRED_ID]) {
                        sh '''
                          echo ">> Enviando .env para servidor remoto..."
                          scp -o StrictHostKeyChecking=no $ENV_FILE $DEPLOY_USER@$DEPLOY_SERVER:$DEPLOY_PATH/.env
                        '''
                    }
                }
            }
        }

        stage('Deploy Remoto via docker-compose') {
            steps {
                sshagent([env.SSH_CRED_ID]) {
                    sh '''
                      echo ">> Executando docker-compose no servidor remoto..."
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
            echo "✅ Pipeline do microserviço concluído com sucesso!"
        }
        failure {
            echo "❌ Falha no pipeline do microserviço!"
        }
    }
}
