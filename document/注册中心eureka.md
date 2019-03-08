
client->服务api =》client->注册中心->服务api集群
依赖
   <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
        </dependency>
启动类启动一个服务注册中心，只需要一个注解@EnableEurekaServer
@EnableEurekaServer

eureka是一个高可用的组件，它没有后端缓存，每一个实例注册之后需要向注册中心发送心跳（因此可以在内存中完成）
在默认情况下erureka server也是一个eureka client ,必须要指定一个 server
通过eureka.client.registerWithEureka：false和fetchRegistry：false来表明自己是一个eureka server.

通过注解@EnableEurekaClient 表明自己是一个eurekaclient服务提供者
