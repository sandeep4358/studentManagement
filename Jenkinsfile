pipeline{
    agent any
    stages{
        stage('Build'){
            steps{
                echo 'Building stage'
                echo "Running ${env.BUILD_NUMBER} or ${env.JENKINS_URL}"
                sh 'mvn -DskipTests clean package'
            }
        }
        stage('Test'){
            steps{
                echo 'Test'
                sh 'mvn test'
            }
        }
        stage('Deploying'){
            steps{
                echo 'Deploying'
            }
        }
    }
}