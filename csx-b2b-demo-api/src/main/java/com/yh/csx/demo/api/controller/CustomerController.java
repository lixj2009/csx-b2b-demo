package com.yh.csx.demo.api.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yh.csx.common.entity.CommonResponse;
import com.yh.csx.common.model.UploadFile;
import com.yh.csx.common.util.BeanUtils;
import com.yh.csx.demo.core.enums.CooperationModeEnum;
import com.yh.csx.demo.core.enums.CustomerStatusEnum;
import com.yh.csx.demo.core.request.web.CustomerAddReq;
import com.yh.csx.demo.core.response.web.CustomerDetailsResp;
import com.yh.csx.demo.po.CustomerPo;
import com.yh.csx.demo.service.CustomerService;
import com.yh.csx.demo.service.vo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api("客户管理服务")
@RestController
@RequestMapping("/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;

	@ApiOperation("获取客户信息")
	@GetMapping("/get")
	public CommonResponse<CustomerDetailsResp> getCustomer(Long customerId) {
		return super.visit(() -> {
			CustomerPo customer = customerService.getCustomer(customerId);
			if (customer.getCooperationMode().equals(CooperationModeEnum.TEMP.getCode())
					&& (customer.getCustomerStatus().equals(CustomerStatusEnum.PENDING.getCode()) || customer.getCustomerStatus().equals(CustomerStatusEnum.REJECT.getCode()))) {
				customer.setCreditLimit(customer.getTempCreditLimit());
				customer.setTempCreditLimit(BigDecimal.ZERO);
			}
			// 查询客户类型
			String customerType = "MOCK";
			CustomerDetailsResp cusP = BeanUtils.convert(CustomerDetailsResp.class, customer);
			cusP.setUploadFileList(JSON.parseArray(customer.getFilePath(), UploadFile.class));
			cusP.setType(customerType);
			return cusP;
		});
	}

	@ApiOperation("禁用/启用")
	@PostMapping("/enable")
	public CommonResponse enable(Long customerId, Boolean enable) {
		return super.visit(() -> {
			customerService.enable(super.getUserName(), customerId, enable);
		});
	}

	@ApiOperation("新增客户(保存草稿)")
	@PostMapping("/add")
	public CommonResponse addCustomer(@RequestBody @Validated CustomerAddReq req) {
		return super.visit(() -> {
			if (log.isDebugEnabled()) {
				log.debug("新增客户(保存草稿)" + req.toString());
			}
			User user = new User(super.getUserId(), super.getUserName());
			customerService.addCustomer(user, req);
		});
	}

}