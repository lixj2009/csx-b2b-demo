package com.yh.csx.demo.controller;

import com.yh.csx.bsf.core.entity.CommonResponse;
import com.yh.csx.bsf.core.exception.BusinessException;
import com.yh.csx.bsf.redis.annotation.RedisCache;
import com.yh.csx.demo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huang Zhaoping
 * @date 2019/6/20
 */
@RestController
public class ErrorController {

    @GetMapping("/callBiz")
    public CommonResponse<User> callBiz(long userId) throws Exception {
        if (userId != 10) {
            throw new BusinessException("你大爷的错误了");
        }
        return CommonResponse.success();
    }

    @GetMapping("/callError")
    public CommonResponse<User> callError(long userId) throws Exception {
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
