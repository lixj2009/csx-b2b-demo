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
```

