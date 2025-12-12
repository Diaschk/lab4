pipeline {
    agent any
    tools {
        maven 'Maven-3.8.4'
        jdk 'JDK-17'
    }
    
    // ⚡ ЭТА СТРОКА ЗАСТАВЛЯЕТ ПРОВЕРЯТЬ GITHUB
    triggers {
        pollSCM('H/2 * * * *')  // Каждые 2 минуты
    }

    stages {
        stage('Cleanup Workspace') {
            steps {
                cleanWs()
            }
        }

        stage('Checkout from GitHub') {
            steps {
                // ⚡ РАСКОММЕНТИРУЙ И ЗАМЕНИ URL НА СВОЙ
                git branch: 'master',
                    url: 'https://github.com/Diaschk/lab4.git'
                
                // Дополнительно: покажи что скачалось
                sh '''
                    echo "=== ФАЙЛЫ В РЕПОЗИТОРИИ ==="
                    ls -la
                    echo "=== ПОСЛЕДНИЙ КОММИТ ==="
                    git log --oneline -1
                '''
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/**/*.xml'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts artifacts: 'target/*.jar'
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}
