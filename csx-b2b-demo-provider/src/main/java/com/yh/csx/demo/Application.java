package com.yh.csx.demo; /**
 * Application class
 *
 * @author huyalin
 * @date 2019/2/13
 */

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import com.alibaba.dubbo.spring.boot.context.event.DubboBannerApplicationListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDubboConfiguration
@MapperScan(basePackages = "com.yh.csx.demo.dao")
@ComponentScan(basePackages = "com.yh.csx.demo")
public class Application {
    public static void main(String[] args) {

        DubboBannerApplicationListener.setBANNER_MODE(Banner.Mode.OFF);
        SpringApplication.run(Application.class, args);
    }
}
