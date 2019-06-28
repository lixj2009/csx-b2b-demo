package com.yh.csx.demo;

import com.yh.csx.demo.model.request.CustomerAddReq;
import com.yh.csx.demo.provider.CustomerProvider;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author: chejiangyi
 * @version: 2019-06-28 14:18
 * Eureka client 调用其他客户端 api示例
 * 注意要配合properties 的配置使用
 **/
@SpringBootApplication
/**
 * 【关键配置】需要使用的项目api 协议类,一般放provider包下面;多个项目则配置多个
 */
@EnableFeignClients(basePackages = {"com.yh.csx.demo.provider"})//"com.yh.csx.ucenter.provider"
public class EurekaApplication {
    /**
     * 获取注入对象方式一
     */
    @Autowired(required=false)
    CustomerProvider customerProvider;

    public static void main(String[] args) {
        val context = SpringApplication.run(com.yh.csx.demo.Application.class, args);
        /**
         * 获取注入对象方式二
         */
        val provider = context.getBean(CustomerProvider.class);
        provider.addCustomer(new CustomerAddReq());
    }
}
