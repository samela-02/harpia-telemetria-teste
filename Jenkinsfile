pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "harpia-ms-telemetria:latest"
        DEPLOY_USER = "tivic"
        DEPLOY_SERVER = "192.168.1.161"
        DEPLOY_PATH = "/tivic/harpia-ms-telemetria"
        GIT_REPO = "https://github.com/tivic-pdi/harpia-ms-telemetria.git"
        GIT_BRANCH = "deploy"
    }

    stages {
        stage('Clonar Repositório') {
            steps {
                dir('harpia-ms-telemetria') {
                    withCredentials([string(credentialsId: 'npm-auth-token', variable: 'GIT_TOKEN')]) {
                        sh '''
                            echo "Clonando o repositório..."
                            git clone https://${GIT_TOKEN}:x-oauth-basic@${GIT_REPO#https://} -b ${GIT_BRANCH} .
                        '''
                    }
                }
            }
        }

        stage('Copiar .env') {
            steps {
                withCredentials([file(credentialsId: 'harpia-ms-telemetria-env', variable: 'ENV_FILE')]) {
                    sh '''
                        echo "Copiando .env para o repositório..."
                        cp $ENV_FILE harpia-ms-telemetria/.env
                    '''
                }
            }
        }

        stage('Build da Imagem Docker') {
            steps {
                sh '''
                    echo "Iniciando build da imagem do microserviço..."
                    docker build -t $DOCKER_IMAGE ./harpia-ms-telemetria
                '''
            }
        }

        stage('Enviar Imagem para Servidor') {
            steps {
                sshagent(['ssh-cred-id']) {
                    sh '''
                        echo "Enviando imagem para o servidor remoto..."
                        docker save $DOCKER_IMAGE | bzip2 | ssh -o StrictHostKeyChecking=no $DEPLOY_USER@$DEPLOY_SERVER "bunzip2 | docker load"
                    '''
                }
            }
        }

        stage('Copiar .env para Servidor') {
            steps {
                withCredentials([file(credentialsId: 'harpia-ms-telemetria-env', variable: 'ENV_FILE')]) {
                    sshagent(['ssh-cred-id']) {
                        sh '''
                            echo "Copiando .env para o servidor remoto..."
                            scp -o StrictHostKeyChecking=no $ENV_FILE $DEPLOY_USER@$DEPLOY_SERVER:$DEPLOY_PATH/.env
                        '''
                    }
                }
            }
        }

        stage('Deploy Remoto via docker-compose') {
            steps {
                sshagent(['ssh-cred-id']) {
                    sh '''
                        echo "Subindo o serviço no servidor remoto..."
                        ssh -o StrictHostKeyChecking=no $DEPLOY_USER@$DEPLOY_SERVER "
                        cd $DEPLOY_PATH &&
                        docker-compose up -d"
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Deploy do microserviço harpia-ms-telemetria concluído com sucesso!"
        }
        failure {
            echo "❌ Falha no pipeline do microserviço!"
        }
    }
}
