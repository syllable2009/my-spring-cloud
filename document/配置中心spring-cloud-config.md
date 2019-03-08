Spring Cloud Config项目是一个解决分布式系统的配置管理方案。它包含了Client和Server两个部分，
server提供配置文件的存储、以接口的形式将配置文件的内容提供出去，client通过接口获取数据、并依据此数据初始化自己的应用。
Spring cloud使用git或svn存放配置文件，默认情况下使用git.

构建Config Server
添加pom依赖
 <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-config-server</artifactId>
 </dependency>
 
 入口类BootApplication启用@EnableConfigServer
 
 
 
 
 构建Config Server
 默认加载的配置是按这个bootstrap文件名来的,所以配置的信息放在bootstrap.yml中
 