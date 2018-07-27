# 可配置共享的数据源中心
Spring Cloud Config就是我们通常意义上的配置中心。
Spring Cloud Config-把应用原本放在本地文件的配置(本地内存)抽取出来放在中心服务器，本质是配置信息从本地迁移到云端。从而能够提供更好的管理、发布能力。
此处示例为：git下的创建的文件respo下存放各种配置文件：
***-development.yml ***-development.properties
***-production.yml ***-production.properties

# 配置中心服务端 config server提供rest接口
通过@EnableConfigServer启动的配置中心服务端
服务端负责将git中存储的配置文件发布成REST接口，客户端可以从服务端REST接口获取配置。
但客户端并不能主动感知到配置的变化，从而主动去获取新的配置，这需要每个客户端通过POST方法触发各自的/refresh。
server:
  port: 8080
spring:
  application:
    name: config-center-server
  cloud:
    config:
      server:
        git:
          uri: https://github.com/forezp/SpringcloudConfig/   # 配置git仓库的地址
          search-paths: respo # git仓库地址下的相对地址，可以配置多个，用,分割。
          label: master # 配置仓库的分支
          username: # git仓库的账号
          password: # git仓库的密码


证明配置服务中心可以从远程程序获取配置信息。
uri/application/* 可以看到加载信息
http请求地址和资源文件映射如下:
/{application}/{profile}[/{label}]
/{application}-{profile}.yml
/{label}/{application}-{profile}.yml
/{application}-{profile}.properties
/{label}/{application}-{profile}.properties 


# 配置中心客户端 config client
server:
  port: 8081
spring:
  application:
    name: config-center-client
  cloud:
    config:
      uri: http://127.0.0.1:8080
      profile: ${spring.profiles.active:development} # 环境参数,获取配置中心不同文件命名的文件配置
      label: master

         