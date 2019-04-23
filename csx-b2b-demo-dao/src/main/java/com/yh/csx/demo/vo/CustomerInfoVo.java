package com.yh.csx.demo.vo;

import com.yh.csx.demo.po.CustomerPo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CustomerInfoVo extends CustomerPo {

	@ApiModelProperty("日订单额")
	private BigDecimal dayAvgAmount;

	@ApiModelProperty("客单价")
	private BigDecimal orderAvgAmount;

	@ApiModelProperty("月频次")
	private Integer orderCount;

	@ApiModelProperty("未下单天数")
	private Integer noOrderDays;

	@ApiModelProperty("距离")
	private Integer distance;
}
