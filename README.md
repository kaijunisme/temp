# War Application

---

## Podman 指令

```
# 建構IMAGE
podman build -t [IMAGE_NAME] .

# 運行CONATINER
podman run --name [CONTAINER_NAME] -d -p 8080:8080 9990:9990 [IMAGE_NAME]

# 使用指令
podman pull jboss/wildfly:8.2.0.Final
podman run --name jboss82 -d -p 8080:8080 9990:9990 jboss/wildfly:8.2.0.Final
podman run --name wildfly82 -d -p 8080:8080 -p 9990:9990 jboss/wildfly:8.2.0.Final

podman run --name jboss82 -d -p 8080:8080 jboss/wildfly:8.2.0.Final

podman pull postgres

# 創建Volume
podman volume create postgres-data

# 建立網路
podman network create warnetwork

# 建立並啟動容器
podman run --name postgres -p 5432:5432 --restart always -v postgresql-data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=123456 -d postgres
podman run --name postgres -p 5432:5432 --restart always -v postgresql-data:/var/lib/postgresql/data --network warnetwork -e POSTGRES_PASSWORD=123456 -d postgres

podman build -t war_application .
podman run --name war_application -d -p 8080:8080 -p 9990:9990 --network warnetwork war_application
podman run --name war_application -d -p 8080:8080 war_application

podman logs -f war_application

podman rm war_application

```