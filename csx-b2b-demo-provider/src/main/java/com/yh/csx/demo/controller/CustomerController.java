package com.yh.csx.demo.controller;

import com.alibaba.fastjson.JSON;
import com.yh.csx.business.api.entity.CommonResponse;
import com.yh.csx.business.core.util.BeanUtils;
import com.yh.csx.demo.model.enums.CooperationModeEnum;
import com.yh.csx.demo.model.enums.CustomerStatusEnum;
import com.yh.csx.demo.model.po.Customer;
import com.yh.csx.demo.model.request.CustomerAddReq;
import com.yh.csx.demo.model.response.CustomerDetailsResp;
import com.yh.csx.demo.model.vo.UploadFile;
import com.yh.csx.demo.model.vo.User;
import com.yh.csx.demo.provider.CustomerProvider;
import com.yh.csx.demo.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * eureka api contoller 服务注册的例子
 * 参考文档:
 */
@Slf4j
@RestController
public class CustomerController extends BaseController implements CustomerProvider {

	@Autowired
	private CustomerService customerService;

	public CommonResponse<CustomerDetailsResp> getCustomer(Long customerId) {
		return super.visit(() -> {
			log.info("aaaa");
			Customer customer = customerService.getCustomer(customerId);
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


	public CommonResponse enable(Long customerId, Boolean enable) {
		return super.visit(() -> {
			customerService.enable(super.getUserName(), customerId, enable);
		});
	}

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