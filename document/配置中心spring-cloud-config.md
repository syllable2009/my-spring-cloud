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
 
 Spring Cloud Config也提供本地存储配置的方式。我们只需要设置属性spring.profiles.active=native，
 Config Server会默认从应用的src/main/resource目录下检索配置文件。
 也可以通过spring.cloud.config.server.native.searchLocations=file:F:/properties/属性来指定配置文件的位置。
 虽然Spring Cloud Config提供了这样的功能，但是为了支持更好的管理内容和版本控制的功能，还是推荐使用git的方式。
 
 
 构建Config Server
 默认加载的配置是按这个bootstrap文件名来的,所以配置的信息放在bootstrap.yml中
 