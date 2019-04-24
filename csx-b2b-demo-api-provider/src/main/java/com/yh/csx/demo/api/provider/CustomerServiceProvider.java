package com.yh.csx.demo.api.provider;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.yh.csx.common.entity.CommonResponse;
import com.yh.csx.demo.request.CustomerAddReq;
import com.yh.csx.demo.response.CustomerDetailsResp;

// 使用Feign的@FeignClient(“服务名称”)映射服务调用
@FeignClient(value = "demo-api", path = "/customer")
public interface CustomerServiceProvider {

	// 服务中方法的映射路径,确保参数一致
	@GetMapping("/get")
	CommonResponse<CustomerDetailsResp> getCustomer(@RequestParam("customerId") Long customerId);

	@PostMapping("/enable")
	CommonResponse enable(@RequestParam("customerId") Long customerId, @RequestParam("enable") Boolean enable);

	@PostMapping("/add")
	CommonResponse addCustomer(@RequestBody CustomerAddReq req);
}
