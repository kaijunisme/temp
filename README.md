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

podman build -t war_application .
podman run --name war_application -d -p 8080:8080 -p 9990:9990 war_application
podman run --name war_application -d -p 8080:8080 war_application

podman logs -f war_application

podman rm war_application
```