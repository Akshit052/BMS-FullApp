pipeline {
   agent any

   stages {
      stage('Verify Branch') {
         steps {
            echo "$GIT_BRANCH"
         }
      }
      stage('Run TDD Unit Tests') {
         steps {
               // Run unit tests and publish the results
               sh 'mvn test'
         }
      }
      stage('Run BDD Tests') {
         steps {
               // Run unit tests and publish the results
               sh 'mvn verify'
         }
      }
      stage('Build and Package') {
         steps {
            // Compile and package your Spring application
            sh 'mvn clean package'
         }
      }
      stage('SonarQube analysis') {
         steps{
            withSonarQubeEnv('sonarqube-8.9.2') {
               sh "mvn sonar:sonar"
            }
         }
      }
      // stage('Performance Test') {
      //    steps {
      //          script {
      //             // Define JMeter-related variables
      //             def jmeterHome = "/path/to/jmeter" // Set to the path of your JMeter installation
      //             def jmeterTestFile = 'your-test-plan.jmx'
      //             def jmeterResultFile = 'results.jtl'
                  
      //             // Execute the JMeter test
      //             sh(script: """
      //                ${jmeterHome}/bin/jmeter -n -t ${jmeterTestFile} -l ${jmeterResultFile}
      //             """)
                  
      //             // Archive the JTL result file
      //             archiveArtifacts artifacts: "${jmeterResultFile}", onlyIfSuccessful: true
      //          }
      //    }
      // }
      
      // stage('Deploy to Tomcat') {
      //       steps {
      //          // Deploy the WAR file to Tomcat using the Deploy to Container plugin
      //          deploy adapters: [tomcatAdapter(credentialsId: 'tomcat-credentials', url: 'http://localhost:8081/manager/text', path: '/bms', war: 'target/BMS_TeamB-0.0.1-SNAPSHOT.war')], contextPath: '/bms'
      //       }
      // }
      // stage('Deploy to Tomcat') {
      //    steps {
      //          script {
      //             def tomcatAdapters = [
      //                [$class: 'TomcatAdapter', credentialsId: 'tomcat-credentials', url: 'http://localhost:8081/manager/text', path: '/bms', war: 'target/BMS_TeamB-0.0.1-SNAPSHOT.war']
      //             ]
      //             deploy adapters: tomcatAdapters
      //          }
      //    }
      // }
      // stage('Run SonarQube Analysis') {
      //    steps {
      //          script {
      //             def scannerHome = tool 'SonarQubeScanner'; // Change 'SonarQubeScanner' to match the name from Jenkins
      //             withSonarQubeEnv('SonarQube') {
      //                sh "${scannerHome}/bin/sonar-scanner"
      //             }
      //          }
      //    }
      // }
   }
}
