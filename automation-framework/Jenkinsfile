// Declarative Pipeline
pipeline {
    agent {
        // Use a Docker image that has Maven and Java installed.
        // You might need to adjust this based on your Jenkins agent setup and available images.
        // For web tests, ensure the agent/image has browsers installed or use Selenium Grid.
        // For mobile tests, ensure Appium server and necessary emulators/devices are accessible.
        docker {
            image 'maven:3.8.5-openjdk-11' // Example image, choose one that fits your Java/Maven needs
            // args '-v /var/run/docker.sock:/var/run/docker.sock' // If you need Docker-in-Docker for Appium server, etc.
            // args '-v $HOME/.m2:/root/.m2' // To cache Maven dependencies
        }
    }

    environment {
        // Define environment variables that can be used in the pipeline
        // These can be overridden by Jenkins job parameters
        TEST_ENV = "${params.TEST_ENV ?: 'qa'}" // Default to 'qa' if not provided by Jenkins param
        BROWSER = "${params.BROWSER ?: 'chrome'}"
        MOBILE_PLATFORM = "${params.MOBILE_PLATFORM ?: 'Android'}"
        // Add other necessary environment variables here
        // APPIUM_SERVER_URL = "http://appium-server:4723/wd/hub" // Example if Appium is a service
    }

    parameters {
        string(name: 'TEST_ENV', defaultValue: 'qa', description: 'Test environment (e.g., qa, demo, prod)')
        string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser for web tests (e.g., chrome, firefox)')
        string(name: 'MOBILE_PLATFORM', defaultValue: 'Android', description: 'Mobile platform for tests (e.g., Android, iOS)')
        // Add more Jenkins job parameters as needed
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from SCM (e.g., Git)
                // This step is usually automatically handled by Jenkins if the Jenkinsfile is in the SCM root.
                // If not, you might need: git url: 'your-repo-url', branch: 'your-branch'
                checkout scm
                sh 'ls -la' // List files for verification
            }
        }

        stage('Build') {
            steps {
                // Compile the project and download dependencies
                // Using -B for batch mode, -DskipTests to only compile
                sh 'mvn -B clean install -DskipTests'
            }
        }

        stage('Web Tests') {
            // This stage might require an agent with browsers installed or configured to use Selenium Grid
            when {
                // Example: Only run web tests if a specific parameter is true or not set to skip
                expression { params.RUN_WEB_TESTS ?: true }
            }
            steps {
                echo "Running Web Tests on Environment: ${env.TEST_ENV}, Browser: ${env.BROWSER}"
                // Execute TestNG web tests
                // Pass environment and browser as system properties to Maven
                // The -Denv will be picked up by ConfigLoader, -Dbrowser by TestNG suite file or tests
                sh "mvn -B test -Denv=${env.TEST_ENV} -Dbrowser=${env.BROWSER} -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml -Dtest=WebLogin*Tests"
            }
            post {
                always {
                    // Archive test results
                    junit 'target/surefire-reports/*.xml'
                    // archiveArtifacts artifacts: 'target/*.jar', fingerprint: true // Example: archive build artifacts
                }
            }
        }

        stage('Mobile Tests') {
            // This stage requires Appium server to be running and accessible,
            // and emulators/devices configured.
            // This might involve a more complex agent setup or a dedicated agent.
            when {
                // Example: Only run mobile tests if a specific parameter is true or not set to skip
                expression { params.RUN_MOBILE_TESTS ?: true }
            }
            steps {
                echo "Running Mobile Tests on Environment: ${env.TEST_ENV}, Platform: ${env.MOBILE_PLATFORM}"
                // Execute TestNG mobile tests
                // Pass environment and mobile platform details
                // Note: Appium server URL, device name, app path might need to be configured
                // either via Jenkins agent capabilities, environment variables, or properties files.
                // The example MobileLoginTests.java and testng.xml use parameters that can be
                // influenced by system properties or directly from the testng.xml.
                sh """
                    mvn -B test -Denv=${env.TEST_ENV} \
                               -Dmobile.platformName=${env.MOBILE_PLATFORM} \
                               -Dsurefire.suiteXmlFiles=src/test/resources/testng.xml \
                               -Dtest=MobileLogin*Tests
                """
                // -Dmobile.deviceName="<value_from_jenkins_or_agent>" \
                // -Dmobile.appPath="<path_on_agent_or_dynamic>" \
                // -Dappium.server.url="${env.APPIUM_SERVER_URL}" \
            }
            post {
                always {
                    // Archive mobile test results
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }

    post {
        // Global post-build actions
        always {
            echo 'Pipeline finished.'
            // Clean up workspace, send notifications, etc.
        }
        success {
            echo 'Pipeline succeeded!'
            // mail to: 'team@example.com', subject: "SUCCESS: Pipeline '${currentBuild.fullDisplayName}'"
        }
        failure {
            echo 'Pipeline failed.'
            // mail to: 'team@example.com', subject: "FAILURE: Pipeline '${currentBuild.fullDisplayName}'"
        }
    }
}
