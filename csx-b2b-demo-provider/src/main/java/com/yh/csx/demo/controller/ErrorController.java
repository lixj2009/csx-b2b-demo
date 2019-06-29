package com.yh.csx.demo.controller;

import com.yh.csx.bsf.redis.annotation.RedisCache;
import com.yh.csx.business.api.entity.CommonResponse;
import com.yh.csx.demo.model.User;
import com.yh.csx.demo.provider.ErrorProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang Zhaoping
 * @date 2019/6/20
 */
@RestController
public class ErrorController implements ErrorProvider {

    @GetMapping("/callBiz")
    public CommonResponse<String> callBiz() {
        return CommonResponse.success("你大爷的错误了");
    }

    @GetMapping("/callError")
    public CommonResponse<User> callError(long userId) {
        if (userId != 10) {
            throw new IllegalArgumentException("你大爷的错误了");
        }
        return CommonResponse.success();
    }

    @GetMapping("/callArray")
    @RedisCache(timeout = 60)
    public String[] callArray(int length) {
        System.out.println("call");
        if (length != 10) {
            throw new IllegalArgumentException("length != 10");
        }
        return new String[length];
    }

}
