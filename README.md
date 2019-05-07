# csx-b2b-demo

彩食鲜标准项目模板（脚手架）,所有的项目都必须按照这个项目模板构建。
同时所有的第三方比如elk,cat,sharding-jdbc都会集成到这个项目结构中,并推广到各个项目中集成。

## 通过脚手架初始化项目
脚手架地址: /doc/client.sh
```
# 格式说明: 
# curl -O  http://10.252.192.3/csx-public/csx-b2b-demo/raw/{分支名:默认master}/doc/client.sh
# sh client.sh {项目名:默认demo,必填} {分支名:默认master,可不填}

curl -O  http://10.252.192.3/csx-public/csx-b2b-demo/raw/master/doc/client.sh
sh client.sh test
```

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

## mybatis代码生成参考知识库
* http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e01685ef34d3000d4.html


## elk集成说明
* http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e016a6888a08918e1.html
  
## pinpoint 部署集成探针示例
* http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e016a47dd82761517.html

## 公开第三方接口给其他应用调用
1. cd csx-b2b-demo-api-provider  #进入api-provider
2. mvn clean install #编译api-provider
3. mvn deploy  #部署到nesus 给第三方引用

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

## jenkins 部署示例
* 正常: csx_dev_demo (dev是开发环境)
* 回滚: csx_dev_demo_rollback

## sonar 代码质量检测集成示例
* http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e016a86812bf319e0.html
  
## 注意事项
1.  项目采用jenkins部署,必须有正常发布，也有回滚发布。
2.  项目部署目录采用web权限用户部署。

##### by 车江毅