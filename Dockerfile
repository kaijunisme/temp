# 使用 WildFly 8.2 作為基底映像檔
FROM jboss/wildfly:14.0.0.Final

# 設定工作目錄
WORKDIR /opt/jboss/wildfly

# 複製 WAR 文件到 JBoss 部署目錄
COPY ./target/war_application-1.0-SNAPSHOT.war standalone/deployments/

# 複製自定義配置（如果需要）
COPY standalone.xml standalone/configuration/

# 開放必要的埠
EXPOSE 8080 9990

# 預設啟動 WildFly
CMD ["sh", "-c", "./bin/standalone.sh -b 0.0.0.0"]
