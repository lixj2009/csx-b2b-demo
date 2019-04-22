# csx-b2b-demo

彩食鲜标准项目模板,所有的项目都必须按照这个项目模板构建。
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

## 安装部署说明
```
/home/web #线上部署目录结构说明,用户web,默认目录归属web用户
    -- jenkins-publish #jenkins自动上传jar的目录
    -- demo #项目demo目录
        -- csx-b2b-demo-webapi-1.0-SNAPSHOT.jar #spring-boot 服务集合打包jar
        -- start.sh #项目start 启动脚本 详细:http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e01688432f6080311.html
        -- backup #jar 包备份
        -- rollback.sh #项目rollback 回滚脚本 详细:http://10.252.192.10:8080/webdoc/view/Pubff808081685d412e016a1092a2f5101a.html
```


## 注意事项
1.  项目采用jenkins部署,有正常发布，也有回滚发布。
2.  项目部署采用web权限用户部署。