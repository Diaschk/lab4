pipeline {
    agent any
    tools {
        maven 'Maven-3.8.4'
        jdk 'JDK-17'
    }

    stages {
        stage('Download from GitHub (NO GIT)') {
            steps {
                cleanWs()
                
                sh '''
                    echo "=== СКАЧИВАЕМ АРХИВ С GITHUB ==="
                    # Скачиваем напрямую ZIP
                    REPO_URL="https://github.com/ТВОЙ-ЮЗЕР/ТВОЙ-РЕПОЗИТОРИЙ"
                    
                    # Скачиваем архив ветки main
                    wget -O repo.zip ${REPO_URL}/archive/refs/heads/main.zip
                    
                    # Или для ветки master
                    # wget -O repo.zip ${REPO_URL}/archive/refs/heads/master.zip
                    
                    unzip -q repo.zip
                    
                    # Перемещаем файлы
                    mv *-main/* . 2>/dev/null || mv *-master/* .
                    rm -rf *-main *-master repo.zip
                    
                    echo "=== ФАЙЛЫ ЗАГРУЖЕНЫ ==="
                    ls -la
                    echo "=== ЕСТЬ POM.XML? ==="
                    find . -name "pom.xml"
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
}
