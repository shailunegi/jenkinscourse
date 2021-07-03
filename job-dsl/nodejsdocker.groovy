job('SNEGINodeJSDockerExample') {
    scm {
        git('git://github.com/shailunegi/DockerDemo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('snegiDSL')
            node / gitConfigEmail('awssnegi23@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('shailunegi/dockerseedjobdemo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('snegidockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
