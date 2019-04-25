package com.yh.csx.demo.controller;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import com.yh.csx.common.entity.CommonResponse;

public abstract class BaseController {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    protected Long getUserId() {
        return 0L;
    }

    protected String getUserName() {
        return "system";
    }

    protected ModelAndView visitPage(Callable<ModelAndView> callable, Option... options) {
        try {
            checkOptions(options);
            return callable.call();
        } catch (Throwable e) {
            log.error("", e);
            return null;
        }
    }

    protected <T> CommonResponse<T> visit(Runnable runnable, Option... options) {
        try {
            checkOptions(options);
            runnable.run();
            return CommonResponse.success();
        } catch (Throwable e) {
            log.error("", e);
            return CommonResponse.error(e);
        }
    }

    protected <T> CommonResponse<T> visit(Callable<T> callable, Option... options) {
        try {
            checkOptions(options);
            return CommonResponse.success(callable.call());
        } catch (Throwable e) {
            log.error("", e);
            return CommonResponse.error(e);
        }
    }

    private void checkOptions(Option[] options) {
        for (Option option : options) {
            if (option == Option.AUTH) {
                assertAuth();
            }
        }
    }
    private void assertAuth() {
    	// TODO
		/*
		 * if (WebUtils.getUser() == null) { throw new BusinessException("请先登录后再请求"); }
		 */
    }

    public enum Option {
        AUTH,
        ROTS
    }
}