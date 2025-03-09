rootProject.name = "springboot340Test"

pluginManagement {
    repositories{
        maven {
            url = uri("http://110.110.110.100:8081/repository/maven-releases/")
            isAllowInsecureProtocol = true
        }
        maven { url=uri ("https://www.jitpack.io")}
        maven { url=uri ("https://maven.aliyun.com/repository/releases")}
        maven { url=uri ("https://maven.aliyun.com/repository/google")}
        maven { url=uri ("https://maven.aliyun.com/repository/central")}
        maven { url=uri ("https://maven.aliyun.com/repository/gradle-plugin")}
        maven { url=uri ("https://maven.aliyun.com/repository/public")}
        google()
        mavenCentral()
        gradlePluginPortal()
    }

}

