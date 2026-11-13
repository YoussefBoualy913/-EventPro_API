
pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'
    }

    stages {

        // 1. Récupération du code depuis GitHub
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        // 2. Compilation, tests et génération du rapport JaCoCo
        stage('Build and Test') {
            steps {
                sh 'mvn clean verify'
            }
        }

        // 3. Publication de la couverture de tests
        stage('JaCoCo Coverage') {
            steps {
                recordCoverage(
                    tools: [[
                        parser: 'JACOCO',
                        pattern: 'target/site/jacoco/jacoco.xml'
                    ]]
                )
            }
        }

       // 4. Analyse de la qualité du code
       stage('SonarQube Analysis') {
           steps {
               withSonarQubeEnv('SonarQube-Local') {
                   sh '''
                       mvn -B org.sonarsource.scanner.maven:sonar-maven-plugin:5.1.0.4751:sonar \
                         -Dsonar.projectKey=eventpro \
                         -Dsonar.projectName=EventPro \
                         -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                   '''
               }
           }
       }

    post {
        always {
            junit(
                testResults: '**/target/surefire-reports/*.xml',
                allowEmptyResults: true
            )
        }

        success {
            echo 'Build reussi !'
        }

        failure {
            echo 'Build echoue !'
        }
    }
}
