package com.yh.csx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.yh.csx.bsf.core.entity.CommonResponse;
import com.yh.csx.bsf.core.model.UploadFile;
import com.yh.csx.bsf.core.util.BeanUtils;
import com.yh.csx.demo.model.enums.CooperationModeEnum;
import com.yh.csx.demo.model.enums.CustomerStatusEnum;
import com.yh.csx.demo.model.po.Customer;
import com.yh.csx.demo.model.response.CustomerDetailsResp;
import com.yh.csx.demo.service.CustomerService;
import com.yh.csx.demo.service.MQExampleSerivice;
import com.yh.csx.demo.service.MessageExampleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author: chejiangyi
 * @version: 2019-06-13 17:38
 **/
@Slf4j
@Api("示例服务:仅测试")
@RestController
@RequestMapping("/example")
public class ExampleController {
    @Autowired
    private MQExampleSerivice mqExampleSerivice;
    @Autowired
    private MessageExampleService messageExampleService;

    @GetMapping("/registerConsumer")
    public void registerConsumer() {
         mqExampleSerivice.registerConsumer();
    }

    @GetMapping("/sendMessage")
    public void sendMessage() {
         mqExampleSerivice.sendMessage();
    }

    @GetMapping("/sendSms")
    public void sendSms() {
        messageExampleService.sendSms(0,"1122");
    }
}
