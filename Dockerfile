ARG BASE_IMAGE

FROM $BASE_IMAGE

# 设置工作目录
WORKDIR /app

# 复制项目的jar包到工作目录
COPY build/libs/springboot340Test-0.0.1-SNAPSHOT.jar /app/app.jar
COPY run.sh /app/run.sh
# 指定容器启动时运行的命令
#ENTRYPOINT ["java", "-jar", "app.jar"]
ENTRYPOINT "java" "-jar" "app.jar"