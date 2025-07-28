pipeline {
    agent any

    environment {
        DOCKER_IMAGE   = "harpia-ms-telemetria:latest"
        DEPLOY_USER    = "tivic"
        DEPLOY_SERVER  = "192.168.1.161"
        DEPLOY_PATH    = "/tivic/harpia-ms-telemetria"
        SSH_CRED_ID    = "ssh-cred-id"           // Credencial SSH configurada no Jenkins
        ENV_CRED_ID    = "env-ms-telemetria"     // Secret file com o .env para o microserviço
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
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
