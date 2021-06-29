pipeline {
  agent any
  stages {
    dir("frontend/teampl") {
      stage("Build") {
        steps {
          sh "cd frontend/teampl"
          sh "sudo npm install"
          sh "sudo npm run build"
        }
      }
      stage("Deploy") {
        steps {
          sh "sudo rm -rf /var/www/jenkins-react-app"
          sh "sudo cp -r ${WORKSPACE}/build/ /var/www/teampl/"
        }
      }
    }
  }
}
