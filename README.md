# csx-b2b-demo

彩食鲜标准项目模板（脚手架）,所有的项目都必须按照这个项目模板构建。
同时所有的第三方比如elk,cat,sharding-jdbc都会集成到这个项目结构中,并推广到各个项目中集成。

## 项目结构说明
```
csx-b2b-demo
    -- csx-b2b-demo-core #公共代码
    -- csx-b2b-demo-dao #数据库操作
    -- csx-b2b-demo-service  #业务逻辑
    -- csx-b2b-demo-task #批处理任务
    -- csx-b2b-demo-api  #api端接口
    -- csx-b2b-demo-api-provider #api端第三方调用提供request，response类库
 -- doc #项目资料
 -- README.md #项目文档
```

## 安装部署目录结构标准
```
/home/web #线上部署目录结构说明,用户web,默认目录归属web用户
    -- jenkins-publish #jenkins自动上传jar的目录
    -- demo #项目demo目录
        -- csx-b2b-demo-webapi-1.0-SNAPSHOT.jar #spring-boot 服务集合打包jar
        -- start.sh #项目start 启动脚本 详情如下
        -- backup #jar 包备份
        -- rollback.sh #项目rollback 回滚脚本 详情如下
        -- pinpoint-agent #pinpoint 部署集成 详情如下
```
## 启动脚本和回滚脚本示例
* 启动脚本: http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e01688432f6080311.html
* 回滚脚本: http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e016a1092a2f5101a.html

## pinpoint 部署集成探针示例
* http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e016a47dd82761517.html

## jenkins 部署示例
* 正常: csx_环境_demo 
* 回滚: csx_环境_demo_rollback

## mybatis代码生成参考知识库
* http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e01685ef34d3000d4.html

# ELK集成说明
### 添加依赖
添加Logstash实现将日志上报到ELK  
1、确定logstash版本：根据当前项目的logback版本到
[Maven仓库](https://mvnrepository.com/artifact/net.logstash.logback/logstash-logback-encoder)查找对应的版本  
2、配置pom.xml文件
```xml
<dependency>
    <groupId>net.logstash.logback</groupId>
    <artifactId>logstash-logback-encoder</artifactId>
    <!-- 根据logback来确定版本 -->
    <version>4.11</version>
    <exclusions>
        <exclusion>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
        </exclusion>
        <exclusion>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

### 日志配置
1、日志配置文件有两种：1、logback-spring.xml；2、logback.xml；第一种增加了一些spring支持的标签，比如：springProfile，可以支持多环境的日志配置。  
2、日志配置文件路径：默认放到resources即可，如果需要自定义路径，可通过application.properties中指定。  
3、日志配置文件中添加logstash的appender：
```xml
<appender name="LOGSTASH" class="net.logstash.logback.appender.LogstashTcpSocketAppender">
    <!-- 指定logstash接收端地址和端口 -->
    <destination>10.252.193.3:4560</destination>
    <!-- 设置日志Encoder-->
    <encoder charset="UTF-8" class="net.logstash.logback.encoder.LogstashEncoder" >
        <!-- 自定义字段：appname，用来区分日志所属应用，此处根据项目修改 -->
        <customFields>{"appname":"elk-demo"}</customFields>
    </encoder>
</appender>
```
4、将logstash的appender添加到需要输出日志的地方：
```xml
<springProfile name="dev">
    <!--自定义-->
    <logger name="com.yh.csx.demo.elk" level="DEBUG" additivity="false">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="LOGSTASH"/>
    </logger>
    <root level="INFO">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="FILE" />
        <appender-ref ref="LOGSTASH" />
    </root>
</springProfile>
```

### API拦截
通过Spring拦截器实现对API的访问情况输出到ELK  
1、拦截器实现参考ElkWebInterceptor  
2、Spring Boot配置： 
```java
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ElkWebInterceptor());
    }
}
```
3、Spring Xml配置： 
```xml
<mvc:interceptors>
    <mvc:interceptor>
        <mvc:mapping path="/**" />
        <bean class="com.yh.csx.demo.elk.ElkWebInterceptor"/>
    </mvc:interceptor>
</mvc:interceptors>
```
4、访问 [API测试连接](http://localhost:8080/demo/hello?name=hzp) 验证配置

### 日志查看
登录[Kibana](http://10.252.193.3:5601/app/kibana#/discover)查看日志


  
## 注意事项
1.  项目采用jenkins部署,必须有正常发布，也有回滚发布。
2.  项目部署目录采用web权限用户部署。