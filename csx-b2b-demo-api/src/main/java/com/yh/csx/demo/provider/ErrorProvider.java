package com.yh.csx.demo.provider;

import com.yh.csx.business.api.entity.CommonResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Api("错误测试")
@FeignClient(name = "csx-b2b-demo-provider", path = "/api/error")
@RequestMapping("/error")
public interface ErrorProvider {

	// 服务中方法的映射路径,确保参数一致
	@GetMapping("/callBiz")
	@ApiOperation("测试错误")
	CommonResponse<String> callBiz();
}

