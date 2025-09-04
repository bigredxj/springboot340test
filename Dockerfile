FROM 110.110.110.101:5000/centos7-dev:1.0

LABEL org.opencontainers.image.ref.name="my-sp34:3.0.0" image.author="xuxu"
# 设置工作目录
WORKDIR /app

# 复制项目的jar包到工作目录
COPY build/libs/springboot340Test-0.0.1.jar /app/app.jar
COPY run.sh /bin/run.sh
RUN chmod +x /bin/run.sh
# 指定容器启动时运行的命令
#ENTRYPOINT ["java", "-jar", "app.jar"]
#ENTRYPOINT "java" "-jar" "app.jar"