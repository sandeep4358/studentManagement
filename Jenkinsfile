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
               echo 'Deploying project started'

                deploy adapters: [tomcat9(credentialsId: 'd9f55980-a94e-4f7d-96f3-d3ec495ac706',
                 path: '',
                 url: 'http://192.168.0.102:9090')],
                 contextPath: '/MPSSchool',
                 war: '**/StudentManagment.war'
            }
        }
    }
}