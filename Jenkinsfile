#!/usr/bin/env groovy

node {
    stage('checkout') {
        checkout scm
    }

    stage('check java') {
        sh "java -version"
    }

    stage('clean') {
        sh "chmod +x mvnw"
      //  sh "./mvnw clean"
    }

    stage('install' ) {
        sh "./mvnw install -DskipTests "
    }


    stage('backend tests') {
//        try {
//            sh "./mvnw test"
//        } catch(err) {
//            throw err
//        } finally {
//            junit '**/target/surefire-reports/TEST-*.xml'
//        }
    }



    stage('packaging') {
        sh "./mvnw verify -DskipTests"
        archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
    }

    def dockerImage
    stage('build docker') {
//        dockerImage = docker.build('bookstore')
        sleep(5)
    }
    stage('deploy docker') {
//        dockerImage = docker.build('bookstore')
        sleep(2)
    }









}
