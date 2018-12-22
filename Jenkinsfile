node {



        stage('Checkout') {
            git url: 'https://github.com/Ga13Ou/tp2qualite.git', branch: 'master'
        }

        stage('Build') {
            sh 'mvn clean install'

            def pom = readMavenPom file:'pom.xml'
            print pom.version
            env.version = pom.version
        }

        stage('Image') {
            dir ('discovery-service') {
                def app = docker.build "localhost:5000/discovery-service:${env.version}"
            }
        }

        stage ('Run') {
            docker.image("localhost:5000/discovery-service:${env.version}").run('-p 8761:8080 -h discovery --name discovery')
        }

        stage ('Final') {
            build job: 'account-service-pipeline', wait: false
        }


}