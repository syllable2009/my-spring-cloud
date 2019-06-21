#注册中心
client-> 服务api => client -> 注册中心 ->服务api集群
依赖
   <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
   </dependency>
启动类启动一个服务注册中心，只需要一个注解@EnableEurekaServer
@EnableEurekaServer

通过服务名找到对应的IP地址(IP地址会变，但服务名一般不会变)

eureka是一个高可用的组件，它没有后端缓存，每一个实例注册之后需要向注册中心发送心跳（因此可以在内存中完成）
在默认情况下erureka server也是一个eureka client ,必须要指定一个 server

@EnableEurekaServer
eureka:
  instance:
    hostname: localhost
  client:
    registerWithEureka: false   #是否开启注册服务，因为这里如果为true表示自己注册自己，而自己就是一个服务注册方，没必要自己注册自己
    fetchRegistry: false     #false表示自己端就是注册中心，我的职责就是维护服务实例，并不需要去检索服务
    serviceUrl:
      defaultZone: http://${eureka.instance.hostname}:${server.port}${server.servlet.context-path}/eureka/
  server:
    enable-self-preservation: false  # 自我保护模式关闭

#服务注册
eureka:
  client:
    register-with-eureka: false  #当前微服务不注册到eureka中(只用于消费)
    service-url: 
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/  


通过注解@EnableEurekaClient 表明自己是一个eurekaclient服务提供者

spring:
  application:
    name: service-api（通过此大写服务名来提供服务，restTemplate feign）
    
    
客户端负载均衡(Ribbon)
服务实例的清单在客户端，客户端进行负载均衡算法分配。
(从上面的知识我们已经知道了：客户端可以从Eureka Server中得到一份服务清单，在发送请求时通过负载均衡算法，在多个服务器之间选择一个进行访问)

服务端负载均衡(Nginx)
服务实例的清单在服务端，服务器进行负载均衡算法分配


# 构建高可用的注册中心
多个Eureka除了自己，向除了自己的服务中心注册，在服务提供者中，向每个注册中心注册


  
