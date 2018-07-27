#springboot-启动扫描类
Spring Boot默认会扫描启动类同包以及子包下的注解，启动类上使用@ComponentScan(basePackages={"com.package1","com.package2"})注解进行指定要扫描的包以及要扫描的类。
此时也应该加上启动类所在的包，否则同包下不会被扫描
Spring Boot应用程序在启动后，会遍历CommandLineRunner接口的实例并运行它们的run方法。也可以利用@Order注解（或者实现Order接口）来规定所有CommandLineRunner实例的运行顺序
@Order注解是按value值从小到大顺序执行的。

#springboot-配置文件-多环境配置
Yml和properties二选一即可，在同时拥有时会出现覆盖
Bootstrap全局的配置参数，加载顺序不同而已
application.yml比application.properties相对比较简洁，优雅代码少，相同级别只能出现一次
Yml在配置文件添加三个英文状态下的短横线即可区分，yml文件本身具有区分不同环境的能力
---
spring:
  profiles: dev
properties需要写成application-dev.properties的方式，spring.profiles.active=dev可选择激活那个环境的配置文件
YAML不能通过@PropertySource注解加载。如果需要使用@PropertySource注解的方式加载值，那就要使用properties文件

#springboot-配置文件-配置参数注入
配置参数写在默认的还在配置文件中，可以通过一下两种方式获取
使用@Value("${属性名}")注解来注入配置
@ConfigurationProperties(prefix = "wx")来将配置参数转化成对象，需要在应用类上加伤@EnableConfigurationProperties({ConfigBean.class})该注解，可配合@Configuration等使用
有时我们不愿意把配置都写到application配置文件中，这时需要我们自定义配置文件，比如test.properties:
在最新版本的springboot，需要加这三个注解。@Configuration @PropertySource(value = “classpath:test.properties”) @ConfigurationProperties(prefix = "wx");在1.4版本需要 PropertySource加上location

#springboot-多模块项目
父项目是一个只有pom文件去掉src的工程，里面定义了packaging为pom，子模块，parent和公共属性依赖
 <packaging>pom</packaging>

    <modules>  
    	<module>wx-service</module>
    	<module>wx-service-web</module>
    </modules>  

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.0.0.M7</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring-cloud.version>Finchley.M5</spring-cloud.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>${spring-cloud.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>1.16.20</version>
            </dependency>
        </dependencies>
    </dependencyManagement>
子模块parent父模块并添加自己的依赖，不填写依赖版本继承自父模块

#springboot-单元测试
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
        </dependency>
依赖于spring-boot-start-starter-test编写
@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "test") 
@SpringBootTest(classes = StartUpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest {
    /**
     * @LocalServerPort 提供了 @Value("${local.server.port}") 的代替
     */
    @LocalServerPort
    private int port;
    private URL base;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        String url = String.format("http://localhost:%d/", port);
        System.out.println(String.format("port is : [%d]", port));
        this.base = new URL(url);
    }

    /**
     * 向"/test"地址发送请求，并打印返回结果
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        ResponseEntity<String> response = this.restTemplate.getForEntity(
                this.base.toString() + "/test", String.class, "");
        System.out.println(String.format("测试结果为：%s", response.getBody()));
    }
}

SpringBoot 提供的一个注解@SpringBootTest基本的所有配置都会通过入口类SpringApplication去加载，而注解可以引用入口类的配置。
webEnvironment = SpringBootTest.WebEnvironment.MOCK提供一个Mock的Servlet环境，内置的Servlet容器并没有真实的启动，主要搭配使用@AutoConfigureMockMvc
RANDOM_PORT — 提供一个真实的Servlet环境，也就是说会启动内置容器，然后使用的是随机端口 
DEFINED_PORT — 这个配置也是提供一个真实的Servlet环境，使用的默认的端口，如果没有配置就是8080 
NONE — 这是个神奇的配置，跟Mock一样也不提供真实的Servlet环境
@ActiveProfiles(profiles = "test") 在测试类上面指定profiles，可以改变当前spring 的profile，来达到多环境的测试
还有一种方式，能够捕获在运行的程序中打印的日志。 
当然这个有些场景是可以使用的，当一个测试方法里面调用了很多方法，可以通过打印这些方法的返回结果来匹配打印的测试结果
    @Rule
    // 这里注意，使用@Rule注解必须要用public
    public OutputCapture capture = new OutputCapture();

    @Test
    public void testLogger() {
        System.out.println("sd");
        assertThat(capture.toString(), Matchers.containsString("sd"));
    }

#springboot-