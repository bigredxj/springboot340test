import com.bmuschko.gradle.docker.tasks.image.DockerBuildImage
import com.bmuschko.gradle.docker.tasks.image.DockerPushImage
import kotlin.math.log

plugins {
    java
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
    id("maven-publish")
    id("org.example.plugin") version "3.0.0"
    id("com.netflix.nebula.ospackage") version "11.11.1"
    id("com.bmuschko.docker-remote-api") version "9.4.0"

}
group = "com.example"
version = "0.0.1"

repositories {
    maven {
        url = uri("https://maven.aliyun.com/nexus/content/groups/public/")
    }
    maven {
        url = uri("http://110.110.110.100:8081/repository/maven-releases/")
        isAllowInsecureProtocol = true
    }

    mavenCentral()
}

dependencies {
    implementation("org.example:my-plugin:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web"){
     //   exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
    implementation("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.4")
    implementation("mysql:mysql-connector-java:8.0.28")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("io.micrometer:micrometer-registry-prometheus:1.15.0-M2")

}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "yourRepoName"
            url = uri("http://110.110.110.100:13033/xuxu/my-repository") // 例如：https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/
            credentials {
              //  username = 'yourUsername'
               // password = 'yourPassword'
            }
        }
    }
}



configurations.all {
    //每隔24小时检查远程依赖是否存在更新，这个检查的是SNAPSHOT版本依赖
    resolutionStrategy.cacheChangingModulesFor(24, "hours")
    //每隔10分钟，这个检查的是非SNAPSHOT版本依赖
    resolutionStrategy.cacheDynamicVersionsFor(60, "seconds")
}


val commonLogback = """
    <?xml version="1.0" encoding="UTF-8"?>
    <configuration scan="true">
        <springProperty scope="context" name="applicationName" source="spring.application.name"
                        defaultValue="tdc-default"/>

        <appender name="APPLICATION_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <file>/var/log/${'$'}{applicationName}/application.log</file>
            <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
                <fileNamePattern>/var/log/${'$'}{applicationName}/application-%d{yyyyMMdd}.%i.log</fileNamePattern>
                <maxHistory>20</maxHistory>
                <maxFileSize>65MB</maxFileSize>
                <totalSizeCap>5GB</totalSizeCap>
            </rollingPolicy>
            <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
                <pattern>%date %level [%thread] %logger{40}: %msg%n</pattern>
            </encoder>
        </appender>

        <appender name="AUDIT" class="ch.qos.logback.core.rolling.RollingFileAppender">
            <file>/var/log/${'$'}{applicationName}/audit.audit</file>
            <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
                <fileNamePattern>/var/log/${'$'}{applicationName}/audit-%d{yyyyMMdd}.%i.audit</fileNamePattern>
                <maxHistory>20</maxHistory>
                <maxFileSize>65MB</maxFileSize>
                <totalSizeCap>5GB</totalSizeCap>
            </rollingPolicy>
            <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
                <pattern>%date - %msg%n</pattern>
            </encoder>
        </appender>

        <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
            <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
                <pattern>%date %level [%thread] %logger{40}: %msg%n</pattern>
            </encoder>
        </appender>


        <logger name="AUDIT" level="INFO" additivity="false">
            <appender-ref ref="AUDIT"/>
            <appender-ref ref="STDOUT"/>
        </logger>

        <!-- Edit the log level of the whole application. -->
        <root level="INFO">
            <appender-ref ref="APPLICATION_FILE"/>
            <appender-ref ref="STDOUT"/>
        </root>
        
        <include optional="true" resource="logback-customize.xml"/>
    </configuration>
""".trimIndent()

tasks.register("commonLogbackConfig") {
    dependsOn("processResources")
    doLast {
        val logbackFile = layout.buildDirectory.dir("resources").get().dir("main").asFile.resolve("logback-spring.xml")
        println(logbackFile.path)
        if (logbackFile.createNewFile()) {
            logbackFile.writeText(commonLogback)
        } else {
            println("logback-spring.xml already exists, skip")
        }
    }
}

tasks.named("classes") {
    //dependsOn("commonLogbackConfig")
}


docker {
    url.set("http://110.110.110.101:2375")
}

tasks.create("myBuildImage", DockerBuildImage::class) {
    dependsOn(tasks.bootJar)
    inputDir.set(project.projectDir)
    dockerFile.set(project.projectDir.resolve("Dockerfile"))
    images.add("110.110.110.101:5000/my-sp34:3.0.0")
}

tasks.create("myPushImage", DockerPushImage::class) {
    dependsOn("myBuildImage")
    images.add("110.110.110.101:5000/my-sp34:3.0.0")
}